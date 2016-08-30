package com.spring.xml;

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
                new GenericXmlApplicationContext("spring-mq-unit.xml");
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        //direct模式：接收routing-key=queue_one_key的消息
        //template.convertAndSend("queue_one_key", "hello!");
        //topic模式：以foo.* exchange-key为模版接收消息
        // template.convertAndSend("foo.bar", "hello!");

        while (true) {
            String date1 = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
            String date2 = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
//            template.convertAndSend(date);
            template.convertAndSend("m", date1);
            //没有指定routingkey 则不能发送成功消息
            //template.convertAndSend(date2);
            //fanout模式：在集群范围内的所有consumer都会收到消息
            //template.convertAndSend("hello!");
            System.out.println("send message date1:" + date1);
            System.out.println("send message date2:" + date2);
            Thread.sleep(1000);
        }
    }
}
