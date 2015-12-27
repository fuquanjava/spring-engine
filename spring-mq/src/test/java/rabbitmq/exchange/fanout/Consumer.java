package rabbitmq.exchange.fanout;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import rabbitmq.C;
import rabbitmq.RabbitUtil;

import java.io.IOException;
import java.util.Random;

/**
 * spring-engine 2015/12/26 21:41
 * fuquanemail@gmail.com
 */
public class Consumer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Random random = new Random();
        int i = random.nextInt(10);
        String clientName = "client-" + i;

        Channel channel = RabbitUtil.getChannel();
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
            Thread.sleep(1000);
        }


    }
}
