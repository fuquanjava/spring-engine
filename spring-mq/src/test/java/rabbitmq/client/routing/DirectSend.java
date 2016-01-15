package rabbitmq.client.routing;

import com.rabbitmq.client.Channel;
import rabbitmq.client.RabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * spring-engine 2015/12/27 13:39
 * fuquanemail@gmail.com
 */
public class DirectSend {
    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = RabbitUtil.getChannel();
        //选择 Direct的交换器作为消息provider
        channel.exchangeDeclare("routing-logs", "direct");

        for (int i = 1; i < 100; i++) {
            int n = i % 3;
            String error = " error " + i;
            String info = "info " + i;
            if (n == 0) {
                channel.basicPublish("routing-logs", "error", null, error.getBytes());
                System.err.println("send error:" + error);
            } else {
                channel.basicPublish("routing-logs", "info", null, info.getBytes());
                System.err.println("send info:" + info);

                channel.basicPublish("routing-logs", "debug", null, info.getBytes());
                System.err.println("send info:" + info);

            }

        }
        channel.close();


    }
}
