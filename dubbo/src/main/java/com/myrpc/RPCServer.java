package com.myrpc;

import com.myrpc.service.DemoService;
import com.myrpc.service.impl.DemoServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by fuquan-pc on 2016/4/17.
 * rpc server
 */
public class RPCServer {
    /**
     * 暴露服务
     *
     * @param service 服务实现
     * @param port    服务端口
     * @throws Exception
     */
    public static void service(final Object service, int port) throws Exception {
        ServerSocket server = new ServerSocket(port);
        while (true) {
            try {
                final Socket socket = server.accept();
                serviceHandler(service, socket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void serviceHandler(final Object service, final Socket socket) {
        Thread serviceThread = new Thread() {

            ObjectInputStream input = null;

            ObjectOutputStream output = null;

            @Override
            public void run() {
                try {
                    input = new ObjectInputStream(socket.getInputStream());
                    String methodName = input.readUTF();
                    Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                    Object[] arguments = (Object[]) input.readObject();
                    output = new ObjectOutputStream(socket.getOutputStream());

                    Method method = service.getClass().getMethod(methodName, parameterTypes);
                    Object result = method.invoke(service, arguments);
                    output.writeObject(result);
                    output.flush();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        input.close();
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        serviceThread.start();

    }

    public static void main(String[] args) {
        DemoService demoService = new DemoServiceImpl();
        try {
            System.out.println(" 开启启动服务...");
            service(demoService,8188);
            System.out.println("服务启动成功...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
