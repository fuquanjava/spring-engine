package system.core;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * fuquanemail@gmail.com 2016/3/24 15:09
 * description:
 * 1.0.0
 */
public class DefaultContainer {

    private static ClassPathXmlApplicationContext context;

    private static String configPath = "classpath:spring-config.xml";

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext(new String[] { configPath });
        context.start();
    }

}
