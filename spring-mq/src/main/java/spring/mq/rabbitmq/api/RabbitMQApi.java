package spring.mq.rabbitmq.api;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.QueueingConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.mq.rabbitmq.RabbitMQConnection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * fuquanemail@gmail.com
 */
public class RabbitMQApi {

    public static final String LONG_DATE_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

    private Logger log = LoggerFactory.getLogger(RabbitMQApi.class);

    private final static String QUEUE_NAME = "hello";

    private final static String DURABLE_QUEUE_NAME = "durable.hello";

    public void sendOne() throws Exception {
        Connection connection = RabbitMQConnection.connection;

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";

        /**
         * 使用默认交换机
         *
         * The default exchange is implicitly bound to every queue, with a routing key equal to the queue name.
         * It it not possible to explicitly bind to, or unbind from the default exchange. It also cannot be deleted.
         */
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }

    /**
     * 默认情况下，RabbitMQ会把每个消息以此轮询发到各个消费者那，把消息平均的发到各个消费者那。这种分配管理的方式叫轮询.
     * @throws Exception
     */
    public void send() throws Exception {
        Connection connection = RabbitMQConnection.connection;

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        while (true) {
            String message = "Hello World!" + formatDate(new Date());
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");


            Thread.sleep(1000);
        }

    }

    /**
     *  1. 生产者和消费者都需要使用queueDeclare方法来指定持久化属性。
     *  2. 通过设置属性MessageProperties. PERSISTENT_TEXT_PLAIN
     *
     *  标记消息持久化并不能百分百的保证消息一定不会被丢失，虽然RabbitMQ会把消息写到磁盘上，但是从RabbitMQ接收到消息到写到磁盘上，
     *  这个短时间的过程中发生的RabbitMQ重启依然会使得为写入到磁盘的消息被丢失。
     *
     *  事实上是这样的，RabbitMQ接收到消息后，首先会把该消息写到内存缓冲区中，并不是直接把单条消息实时写到磁盘上的。
     *
     *  消息的持久化不是健壮的，但是对于简单的任务队列是够用了
     *
     * @throws Exception
     */
    public void durableQueue() throws Exception{
        Connection connection = RabbitMQConnection.connection;

        Channel channel = connection.createChannel();

        // true: 表示队列是持久的，RabbitMQ重启不会丢失队列.
        boolean durable = true;

        // 生产者和消费者都需要使用queueDeclare方法来指定持久化属性。
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

        String message = "Hello durable queue!";

        channel.basicPublish("", DURABLE_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }


    private static String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(LONG_DATE_FORMAT_STR);
        return dateFormat.format(date);
    }

    /**
     * 自动 ack 机制
     * @param receiveName
     * @throws Exception
     */
    public void receiveAutoAck(String receiveName) throws Exception {
        Connection connection = RabbitMQConnection.connection;

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        QueueingConsumer consumer = new QueueingConsumer(channel);

        channel.basicConsume(QUEUE_NAME, true, consumer);
        log.info("[{}] Waiting for messages", receiveName);


        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            log.info("[{}] received message = {}" , receiveName, message);
        }
    }


    /**
     * RabbitMQ在消息被生产者推送过来后就被推送到消费者端，它不会去查看未接收到消费者确认的消息数量。它只会把N个消息均与的分发到N个消费者那。
     *
     * 使用basicQos放来来设置消费者最多会同时接收多少个消息。这里设置为1，表示RabbitMQ同一时间发给消费者的消息不超过一条.
     * @param receiveName
     * @throws Exception
     */
    public void basicQos(String receiveName) throws Exception {
        Connection connection = RabbitMQConnection.connection;

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.basicQos(1);

        QueueingConsumer consumer = new QueueingConsumer(channel);

        channel.basicConsume(QUEUE_NAME, true, consumer);
        log.info("[{}] Waiting for messages", receiveName);


        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            log.info("[{}] received message = {}" , receiveName, message);
        }
    }


    /**
     * 手动 ack 机制
     * @param receiveName
     * @throws Exception
     */
    public void receiveAck(String receiveName) throws Exception {
        Connection connection = RabbitMQConnection.connection;

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        QueueingConsumer consumer = new QueueingConsumer(channel);

        // false : 手动ack
        channel.basicConsume(QUEUE_NAME, false, consumer);
        log.info("[{}] Waiting for messages", receiveName);


        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());

            doMsg(message, receiveName);

            // 手动ack
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

    private void doMsg(String message, String receiveName ) throws InterruptedException {
        log.info("[{}] received message = {}" , receiveName, message);
        Thread.sleep(3000);
    }
}
