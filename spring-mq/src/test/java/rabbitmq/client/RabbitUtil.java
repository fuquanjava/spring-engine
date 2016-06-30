package rabbitmq.client;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * spring-engine 2015/12/26 16:46
 * fuquanemail@gmail.com
 */
public class RabbitUtil {
    public static Connection connection;
    static {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.2.13.54");
        try {
            connection  = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }


    public static Channel getChannel(){
        try {
            return connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("aaaa");
        }
    }

}
