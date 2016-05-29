package javase.proxy;


import javase.proxy.support.UserService;
import javase.proxy.support.UserServiceImpl;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * fuquanemail@gmail.com 2016/5/10 16:39
 * description:
 * 1.0.0
 */
public class ProxyGeneratorTest {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        generateClassFile(userService.getClass(), "UserServiceProxy");
    }

    public static void generateClassFile(Class clazz, String proxyName) {
        //根据类信息和提供的代理类名称，生成字节码
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
        String paths = clazz.getResource(".").getPath();
        System.out.println(paths);
        FileOutputStream out = null;

        try {
            //保留到硬盘中
            out = new FileOutputStream(paths + proxyName + ".class");
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
