package rabbitmq.test_1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * fuquanemail@gmail.com 2015/12/15 10:11
 * description:
 * 1.0.0
 */
public class SendMsg {
    //队列名称
    private final static String QUEUE_NAME = "hello2";

    public static void main(String[] argv) {
        /**
         * 创建连接连接到MabbitMQ
         */
        ConnectionFactory factory = new ConnectionFactory();
        //设置MabbitMQ所在主机ip或者主机名
        factory.setHost("127.0.0.1");
        //创建一个连接
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建一个
        Channel channel = null;
        // Channel 的并发问题:
        // Channel的实例不能在线程间共享, application 更应该是每个线程都有自己的channel,而不是多线程指尖共享.
        // 如果多线程公用channel 会导致不正确的 frame 在传递
        //  As such, applications need to use a {@link Channel} per thread.

        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //指定一个队列
        //值得注意的是队列只会在它不存在的时候创建，多次声明并不会重复创建。
        try {
            // //定义队列类型，这是一个幂等的操作，它只有在该queue不存在的时候才起作用。无论在生产和消费都要定义，而且生产和消费的定义需要一致
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            // //参数1队列名，参数2是否支持持久化，参数3是否为excluse队列（仅连接者可见且一旦断开就自动删除），参数4是否自动删除（没有任何消费者的话便队列便删除），参数5其他属性
        } catch (IOException e) {
            e.printStackTrace();
        }

        //往队列中发出一条消息
        try {
            int i = 10000;
            while (i > 0) {
                //发送的消息
                String message = "hello , " + i;
                System.out.println(" [x] Sent " + message);
                //信息的内容是字节数组，也就意味着你可以传递任何数据
                // //参数1指定exchange，参数2指定routingKey,这里可以理解为队列名，参数3消息类型
                //channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));

                i--;
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //关闭频道和连接
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
