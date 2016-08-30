package spring.mq.rabbitmq.routing;

import com.rabbitmq.client.*;
import spring.mq.rabbitmq.RabbitMQConnection;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * fuquanemail@gmail.com
 */

public class DirectExchange {

    private ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 3);

    static final String direct_exchange_name = "direct.exchange";
    static final String direct_queue_name = "direct.queue";

    public void sender() throws IOException {

        Connection connection = RabbitMQConnection.connection;
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(direct_exchange_name, "direct");

        // 声明一个队列，可以从 控制台创建。
        // channel.queueDeclare("amq.direct.exchange" , true, true,false , null);

        channel.queueBind(direct_queue_name, direct_exchange_name, "");

        int messageId = 1;

        while (true) {

            byte[] messageBodyBytes = (messageId + "- hello").getBytes();

            channel.basicPublish(direct_exchange_name, "", MessageProperties.PERSISTENT_TEXT_PLAIN, messageBodyBytes);

            messageId++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ConsumerThread extends Thread {

        Connection connection = null;
        String name;

        public ConsumerThread(Connection connection, String name) {
            this.connection = connection;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Channel channel = connection.createChannel();
                //channel.exchangeDeclare(direct_exchange_name, "direct");

                //channel.queueBind(direct_queue_name, direct_exchange_name, "");

                channel.queueDeclare(direct_queue_name, false, false, false, null);

                Consumer consumer = new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String message = new String(body, "UTF-8");
                        System.out.println(" - Received '" + envelope.getRoutingKey() + "':'" + message + "'");
                    }
                };
                channel.basicConsume(direct_queue_name, true, consumer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void consumer() throws Exception {
        Connection connection = RabbitMQConnection.connection;

        // ConsumerThread c1 = new ConsumerThread(connection, "线程1");

        Channel channel = connection.createChannel();
        //channel.exchangeDeclare(direct_exchange_name, "direct");

        //channel.queueBind(direct_queue_name, direct_exchange_name, "");

        channel.queueDeclare(direct_queue_name, true, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" - Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };
        channel.basicConsume(direct_queue_name, true, consumer);


    }

    public static void main(String[] args) throws InterruptedException {
        Connection connection = RabbitMQConnection.connection;


        ConsumerThread t1 = new ConsumerThread(connection, "消费1");
        t1.start();

        Thread.currentThread().join();
    }
}
