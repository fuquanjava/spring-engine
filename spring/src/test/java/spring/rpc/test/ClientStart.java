package spring.rpc.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.rpc.server.RpcProxy;
import spring.rpc.service.HelloService;


/**
 * fuquanemail@gmail.com 2016/5/24 13:02
 * description:
 * 1.0.0
 */
public class ClientStart {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        RpcProxy rpcProxy = context.getBean(RpcProxy.class);
        HelloService helloService = rpcProxy.create(HelloService.class);
        String result = helloService.sayHello("hihi");
        System.err.println(result);

    }
}
