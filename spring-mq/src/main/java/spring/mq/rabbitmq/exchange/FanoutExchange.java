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
 任何发送到Fanout Exchange的消息都会被转发到与该Exchange绑定(Binding)的所有Queue上。

 1.可以理解为路由表的模式

 2.这种模式不需要RouteKey

 3.这种模式需要提前将Exchange与Queue进行绑定，一个Exchange可以绑定多个Queue，一个Queue可以同多个Exchange进行绑定。

 4.如果接受到消息的Exchange没有与任何Queue绑定，则消息会被抛弃。
 */
public class FanoutExchange {

    private Logger log = LoggerFactory.getLogger(FanoutExchange.class);

    static final String fanout_exchange_name = "fanout.exchange";
    static final String fanout_queue_name = "fanout.queue";


    public void sender() throws IOException {

        Connection connection = RabbitMQConnection.connection;
        Channel channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare(fanout_exchange_name, "fanout");

        //定义队列类型，这是一个幂等的操作，它只有在该queue不存在的时候才起作用。无论在生产和消费都要定义，而且生产和消费的定义需要一致.
        //参数1队列名，参数2是否支持持久化，参数3是否为excluse队列（仅连接者可见且一旦断开就自动删除），参数4是否自动删除（没有任何消费者的话便队列便删除），参数5其他属性
        channel.queueDeclare(fanout_queue_name, false, false, false, null);

        // 交换机 队列绑定
        channel.queueBind(fanout_queue_name, fanout_exchange_name,"");

        int messageId = 1;

        while (true) {

            String message = messageId + "- hello fanout!";

            channel.basicPublish(fanout_exchange_name, "", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

            // 如果 routing_key不匹配，则丢失消息.
            //channel.basicPublish(fanout_exchange_name, "abc", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

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

        channel.queueDeclare(fanout_queue_name, false, false, false, null);

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
        channel.basicConsume(fanout_queue_name, true, consumer);
    }

    public static void main(String[] args) throws InterruptedException {
    }
}
