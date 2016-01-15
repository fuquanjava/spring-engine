package spring.rabbitmq;

import org.apache.http.client.utils.DateUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;

/**
 * fuquanemail@gmail.com 2016/1/7 19:12
 * description:
 * 1.0.0
 */
public class Producer {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context =
                new GenericXmlApplicationContext("spring.xml");
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        //direct模式：接收routing-key=queue_one_key的消息
        //template.convertAndSend("queue_one_key", "hello!");
        //topic模式：以foo.* routing-key为模版接收消息
        // template.convertAndSend("foo.bar", "hello!");

        while (true) {
            String date = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
            template.convertAndSend(date);
            //fanout模式：在集群范围内的所有consumer都会收到消息
            //template.convertAndSend("hello!");
            System.out.println("send message:" + date);
            Thread.sleep(2000);
        }
    }
}
