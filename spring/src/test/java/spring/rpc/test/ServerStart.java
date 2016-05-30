package spring.rpc.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * fuquanemail@gmail.com 2016/5/29 17:11
 * description:
 * 1.0.0
 */
public class ServerStart {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring/spring-rpc-server.xml");
        context.start();


    }
}
