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
    private final static String QUEUE_NAME="log";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // 创建连接和频道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();



    }
    static class SendThread extends Thread{
        private Connection connection;
        public SendThread(Connection connection){
            this.connection = connection;
        }
        @Override
        public void run() {
            try {
                Channel channel =
                        this.connection.createChannel();


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
