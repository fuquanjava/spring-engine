package system.test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * fuquanemail@gmail.com 2016/3/11 15:37
 * description:
 * 1.0.0
 */
public class QuartzTest {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        context.start();
        System.err.println(" quartz 启动成功");


    }
}
