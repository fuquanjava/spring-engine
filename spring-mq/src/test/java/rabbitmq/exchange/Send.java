package rabbitmq.exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * fuquanemail@gmail.com 2015/12/16 14:52
 * description:
 * 1.0.0
 */
public class Send {
    private final static String EXCHANGE_NAME = "ex_log";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("127.0.0.1");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 声明转发器和类型
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        int i = 1 ;
        while (i< 100){
            String message = "hello " + i;
            i ++;
            // 往转发器上发送消息
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            Thread.sleep(1000);
        }
        channel.close();
        connection.close();


    }
}
