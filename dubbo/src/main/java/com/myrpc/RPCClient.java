package com.myrpc;

import com.myrpc.service.DemoService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * Created by fuquan-pc on 2016/4/17.
 * rpc client
 */
public class RPCClient {

    /**
     * 引用服务
     *
     * @param <T>            接口泛型
     * @param interfaceClass 接口类型
     * @param host           服务器主机名
     * @param port           服务器端口
     * @return 远程服务
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T reference(final Class<T> interfaceClass, final String host, final int port) throws Exception {

        /**
         * 参考 JDKProxyFactory
         */

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
                Socket socket = new Socket(host, port);
                try {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    try {
                        output.writeUTF(method.getName());
                        output.writeObject(method.getParameterTypes());
                        output.writeObject(arguments);
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        try {
                            Object result = input.readObject();
                            if (result instanceof Throwable) {
                                throw (Throwable) result;
                            }
                            return result;
                        } finally {
                            input.close();
                        }
                    } finally {
                        output.close();
                    }
                } finally {
                    socket.close();
                }
            }
        });
    }



    public static void main(String[] args) {
        try {
            System.out.println("start client");
            DemoService demoService = reference(DemoService.class,"127.0.0.1", 8188);
            demoService.sayHello();
            System.out.println(" invoke success");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
