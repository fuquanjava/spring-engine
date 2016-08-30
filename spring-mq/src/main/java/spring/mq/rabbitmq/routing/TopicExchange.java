package spring.mq.rabbitmq.routing;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.mq.rabbitmq.RabbitMQConnection;

import java.io.IOException;

/**
 * fuquanemail@gmail.com
 */

/**
 任何发送到Topic Exchange的消息都会被转发到所有关心RouteKey中指定话题的Queue上

 1.这种模式较为复杂，简单来说，就是每个队列都有其关心的主题，所有的消息都带有一个“标题”(RouteKey)，Exchange会将消息转发到所有关注主题能与RouteKey模糊匹配的队列。

 2.这种模式需要RouteKey，也许要提前绑定Exchange与Queue。

 3.在进行绑定时，要提供一个该队列关心的主题，如“#.log.#”表示该队列关心所有涉及log的消息(一个RouteKey为”MQ.log.error”的消息会被转发到该队列)。

 4.“#”表示0个或若干个关键字，“*”表示一个关键字。如“log.*”能与“log.warn”匹配，无法与“log.warn.timeout”匹配；但是“log.#”能与上述两者匹配。

 5.同样，如果Exchange没有发现能够与RouteKey匹配的Queue，则会抛弃此消息。
 */
public class TopicExchange {

    private Logger log = LoggerFactory.getLogger(TopicExchange.class);

    static final String direct_exchange_name = "topic.exchange";
    static final String direct_queue_name = "topic.queue";
    static final String direct_routing_key="#.topic.info.#";

    public void sender() throws IOException {

        Connection connection = RabbitMQConnection.connection;
        Channel channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare(direct_exchange_name, "topic");

        //定义队列类型，这是一个幂等的操作，它只有在该queue不存在的时候才起作用。无论在生产和消费都要定义，而且生产和消费的定义需要一致.
        //参数1队列名，参数2是否支持持久化，参数3是否为excluse队列（仅连接者可见且一旦断开就自动删除），参数4是否自动删除（没有任何消费者的话便队列便删除），参数5其他属性
        channel.queueDeclare(direct_queue_name, false, false, false, null);

        // 交换机 队列绑定
        channel.queueBind(direct_queue_name, direct_exchange_name, direct_routing_key);

        int messageId = 1;

        while (true) {

            String message = messageId + "-hello topic.";

            channel.basicPublish(direct_exchange_name, direct_routing_key, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

            log.info("send msg = {}", message);

            messageId++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consumer(final String name) throws Exception {

        Connection connection = RabbitMQConnection.connection;

        Channel channel = connection.createChannel();

        channel.queueDeclare(direct_queue_name, false, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                log.info("[{}] received msg = {}", name, message);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        channel.basicConsume(direct_queue_name, true, consumer);
    }

    public static void main(String[] args) throws InterruptedException {
    }
}
