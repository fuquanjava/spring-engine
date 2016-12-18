package spring.rpc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import spring.rpc.domain.RpcRequest;
import spring.rpc.domain.RpcResponse;
import spring.rpc.util.SerializationUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fuquanemail@gmail.com 2016/5/12 10:27
 * description:
 * 1.0.0
 */
public class RpcServer implements ApplicationContextAware, InitializingBean {

    private static final Logger LOG = LoggerFactory.getLogger(RpcServer.class);

    private Map<String, Object> handlerMap = new HashMap<>(); // 存放接口名与服务对象之间的映射关系

    private String serverAddress;
    private ServiceRegistry serviceRegistry;

    public RpcServer(String serverAddress, ServiceRegistry serviceRegistry) {
        this.serverAddress = serverAddress;
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {

        Map<String, Object> serviceBeanMap = ctx.getBeansWithAnnotation(RpcServiceAnnotation.class); // 获取所有带有 RpcService 注解的 Spring Bean
        if (MapUtils.isNotEmpty(serviceBeanMap)) {
            for (Object serviceBean : serviceBeanMap.values()) {
                String interfaceName = serviceBean.getClass().getAnnotation(RpcServiceAnnotation.class).value().getName();

                handlerMap.put(interfaceName, serviceBean);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline()
                                    .addLast(new RpcDecoder(RpcRequest.class)) // 将 RPC 请求进行解码（为了处理请求）
                                    .addLast(new RpcEncoder(RpcResponse.class)) // 将 RPC 响应进行编码（为了返回响应）
                                    .addLast(new RpcHandler(handlerMap)); // 处理 RPC 请求
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            String[] array = serverAddress.split(":");
            String host = array[0];
            int port = Integer.parseInt(array[1]);

            ChannelFuture future = bootstrap.bind(host, port).sync();
            LOG.debug("server started on port {}", port);

            if (serviceRegistry != null) {
                serviceRegistry.register(serverAddress); // 注册服务地址
            }

            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    /**
     * 解码
     */
    public static class RpcDecoder extends ByteToMessageDecoder {

        private Class<?> genericClass;

        public RpcDecoder(Class<?> genericClass) {
            this.genericClass = genericClass;
        }

        @Override
        public final void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
            if (in.readableBytes() < 4) {
                return;
            }
            in.markReaderIndex();
            int dataLength = in.readInt();
            if (dataLength < 0) {
                channelHandlerContext.close();
            }
            if (in.readableBytes() < dataLength) {
                in.resetReaderIndex();
            }
            byte[] data = new byte[dataLength];
            in.readBytes(data);

            Object obj = SerializationUtil.deserialize(data, genericClass);
            list.add(obj);
        }
    }

    /**
     * 编码
     */
    public static class RpcEncoder extends MessageToByteEncoder {

        private Class<?> genericClass;

        public RpcEncoder(Class<?> genericClass) {
            this.genericClass = genericClass;
        }


        @Override
        protected void encode(ChannelHandlerContext channelHandlerContext, Object in, ByteBuf byteBuf) throws Exception {
            if (genericClass.isInstance(in)) {
                byte[] data = SerializationUtil.serialize(in);
                byteBuf.writeInt(data.length);
                byteBuf.writeBytes(data);
            }
        }
    }

    public static class RpcHandler extends SimpleChannelInboundHandler<RpcRequest> {

        private final Map<String, Object> handlerMap;

        public RpcHandler(Map<String, Object> handlerMap) {
            this.handlerMap = handlerMap;
        }


        @Override
        protected void channelRead0(ChannelHandlerContext ctx, RpcRequest request) throws Exception {
            RpcResponse response = new RpcResponse();
            response.setRequestId(request.getRequestId());
            try {
                Object result = handle(request);
                response.setResult(result);
            } catch (Throwable t) {
                response.setError(t);
            }
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);


        }

        /**
         * 为了避免使用 Java 反射带来的性能问题，我们可以使用 CGLib 提供的反射 API，如上面用到的FastClass与FastMethod。
         *
         * @param request
         * @return
         * @throws Throwable
         */
        private Object handle(RpcRequest request) throws Throwable {
            String className = request.getClassName();
            Object serviceBean = handlerMap.get(className);

            Class<?> serviceClass = serviceBean.getClass();
            String methodName = request.getMethodName();
            Class<?>[] parameterTypes = request.getParameterTypes();
            Object[] parameters = request.getParameters();

        /*Method method = serviceClass.getMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(serviceBean, parameters);*/

            FastClass serviceFastClass = FastClass.create(serviceClass);
            FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
            return serviceFastMethod.invoke(serviceBean, parameters);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            LOG.error("server caught exception", cause);
            ctx.close();
        }
    }

}
