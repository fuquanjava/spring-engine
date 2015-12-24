package rabbitmq.ack;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * fuquanemail@gmail.com 2015/12/15 14:53
 * description:
 * 1.0.0
 */
public class AckMsg {
    private final static String EXCHANGE_NAME = "ex_log";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        FanoutEx ex1 = new FanoutEx(" thread  1");
        FanoutEx ex2 = new FanoutEx(" thread  2");

        ex1.start();
        ex2.start();

    }

    static class FanoutEx extends Thread {
        private String threaName;

        public FanoutEx(String threaName) {
            this.threaName = threaName;
        }

        @Override
        public void run() {
            // 创建连接和频道
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = null;
            try {
                connection = factory.newConnection();
                Channel channel = connection.createChannel();

                channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

                // 创建一个非持久的、唯一的且自动删除的队列
                String queueName = channel.queueDeclare().getQueue();
                // 为转发器指定队列，设置binding
                channel.queueBind(queueName, EXCHANGE_NAME, "");

                System.out.println(threaName + " Waiting for messages. To exit press CTRL+C");

                QueueingConsumer consumer = new QueueingConsumer(channel);

                // 指定接收者，第二个参数为自动应答，无需手动应答
                channel.basicConsume(queueName, true, consumer);

                while (true) {
                    QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                    String message = new String(delivery.getBody());
                    System.err.println(threaName + " msg ," + message);
                    Thread.sleep(1000);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
