package spring.mq.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * fuquanemail@gmail.com
 */
public class RabbitMQConnection {

    public static Connection connection;

    static final String host = "127.0.0.1";

    static {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        //actory.setUsername("");
        //factory.setVirtualHost("/");

        System.out.println(factory);
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
