package rabbitmq.client.exchange.fanout;

import com.rabbitmq.client.*;
import rabbitmq.client.C;
import rabbitmq.client.RabbitUtil;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * spring-engine 2015/12/26 21:41
 * fuquanemail@gmail.com
 */
public class Consumer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Random random = new Random();
        int i = random.nextInt(10);
        String clientName = "client-" + i;

        ConnectionFactory factory = new ConnectionFactory();
        //factory.setHost("10.2.13.54");
        factory.setHost("192.168.1.128");
        factory.setUsername("root");
        factory.setPassword("root");

        Channel channel = null;
        try {
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        //申明交换器
        channel.exchangeDeclare(C.FANOUT_EXCHANGE,"fanout");

        //匿名队列
        String queueName = channel.queueDeclare().getQueue();
        System.err.println("queueName:" + queueName);

        //绑定队列到交换器
        AMQP.Queue.BindOk bindOk = channel.queueBind(queueName,C.FANOUT_EXCHANGE,"");
        System.err.println("bindOk:"+ bindOk);

        System.out.println(clientName+"  Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);

        channel.basicConsume(queueName, true, consumer);

        while (true) {

            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            System.out.println(clientName+ " Received :" + message + "");
            Thread.sleep(3000);
        }


    }
}
