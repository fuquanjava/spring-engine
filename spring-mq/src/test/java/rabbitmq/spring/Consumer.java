package rabbitmq.spring;

import org.apache.http.client.utils.DateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;

/**
 * fuquanemail@gmail.com 2016/1/7 19:13
 * description:
 * 1.0.0
 */
public class Consumer {
    public static void main(String[] args) {
        AbstractApplicationContext context =
                new GenericXmlApplicationContext("spring-mq-unit.xml");
        long startTime = context.getStartupDate();
        System.err.println("startTime" + DateUtils.formatDate(new Date(startTime), "YYYY-MM-dd hh:mm:ss"));

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        context.stop();
    }
}
