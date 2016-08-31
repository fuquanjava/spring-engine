package spring.mq.rabbitmq.exchange;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.mq.rabbitmq.RabbitMQConnection;

import java.io.IOException;

/**
 * fuquanemail@gmail.com
 */

/**
 * 任何发送到Direct Exchange的消息都会被转发到RouteKey中指定的Queue。

 1.一般情况可以使用rabbitMQ自带的Exchange："", (该Exchange的名字为空字符串，下文称其为default Exchange)。

 2.这种模式下不需要将Exchange进行任何绑定(binding)操作

 3.消息传递时需要一个“RouteKey”，可以简单的理解为要发送到的队列名字。

 4.如果vhost中不存在RouteKey中指定的队列名，则该消息会被抛弃。

 默认rabbit将队列中的消息均分成n份分发给n个consumer, eg: 消息 m1,m3,m3. 消费 c1,c2. 有可能的情况就是 c1 消费 m1，c2消费m2,m3.
 */
public class DirectExchange {

    private Logger log = LoggerFactory.getLogger(DirectExchange.class);

    static final String direct_exchange_name = "direct.exchange";
    static final String direct_queue_name = "direct.queue";
    static final String direct_routing_key="direct_routing_key";

    public void sender() throws IOException {

        Connection connection = RabbitMQConnection.connection;
        Channel channel = connection.createChannel();

        //声明交换机(默认就是 direct 类型.
        //channel.exchangeDeclare(direct_exchange_name, "direct");

        //定义队列类型，这是一个幂等的操作，它只有在该queue不存在的时候才起作用。无论在生产和消费都要定义，而且生产和消费的定义需要一致.
        //参数1队列名，参数2是否支持持久化，参数3是否为excluse队列（仅连接者可见且一旦断开就自动删除），参数4是否自动删除（没有任何消费者的话便队列便删除），参数5其他属性
        channel.queueDeclare(direct_queue_name, true, false, false, null);

        // 交换机 队列绑定
        //channel.queueBind(direct_queue_name, direct_exchange_name, direct_routing_key);

        int messageId = 1;

        while (true) {

            String message = messageId + "-hello";

            channel.basicPublish("", direct_queue_name, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

            // 如果 routing_key不匹配，则丢失消息.
            //channel.basicPublish(direct_exchange_name, "abc", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

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
}
