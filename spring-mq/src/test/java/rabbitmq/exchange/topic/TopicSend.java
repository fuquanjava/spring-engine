package rabbitmq.exchange.topic;

import com.rabbitmq.client.Channel;
import rabbitmq.C;
import rabbitmq.RabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * spring-engine 2015/12/27 15:51
 * fuquanemail@gmail.com
 */
public class TopicSend {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitUtil.getChannel();
        channel.exchangeDeclare("topic-logs","topic");

        //获取发送消息的routing key
        String[] routType = new String[]{"sys.error","sys","sys.error.error",""};

        while (true){

            String message = C.getMessage();
            channel.basicPublish("topic-logs" , routType[0], null, message.getBytes());
            channel.basicPublish("topic-logs" , routType[1], null, message.getBytes());
            channel.basicPublish("topic-logs" , routType[2], null, message.getBytes());
            channel.basicPublish("topic-logs" , routType[3], null, message.getBytes());
            System.err.println("send message : " + message);
        }


    }
}
