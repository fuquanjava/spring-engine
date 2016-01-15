package rabbitmq.client.rpc;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import rabbitmq.client.RabbitUtil;

import java.io.IOException;

public class RpcClient {

    private Channel channel = RabbitUtil.getChannel();
    private String requestQueueName = "rpc_queue";
    private String replyQueueName;
    private QueueingConsumer consumer;

    public RpcClient() {
        try {
            replyQueueName = channel.queueDeclare().getQueue();
            consumer = new QueueingConsumer(channel);
            channel.basicConsume(replyQueueName, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String call(String message) throws Exception {
        String corrId = java.util.UUID.randomUUID().toString();
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("", requestQueueName, props, message.getBytes());
        String response = null;

        boolean continued = true;
        while (continued) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response = new String(delivery.getBody());
                continued = false;
            }
        }
        return response;
    }
}