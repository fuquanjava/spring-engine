package rabbitmq.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import rabbitmq.RabbitUtil;

import java.io.IOException;

/**
 * spring-engine 2015/12/27 15:59
 * fuquanemail@gmail.com
 */
public class TopicConsumer2 {
    public static void main(String[] args) throws IOException, InterruptedException {

        Channel channel = RabbitUtil.getChannel();
        channel.exchangeDeclare("topic-logs","topic");

        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName,"topic-logs", "sys.#");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            String routingKey = delivery.getEnvelope().getRoutingKey();
            System.out.println("TopicConsumer2  Received '" + routingKey + "':'"
                    + message + "'");
        }

    }
}
