package rabbitmq.spring;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.UnsupportedEncodingException;

/**
 * fuquanemail@gmail.com 2016/1/7 19:09
 * description:
 * 1.0.0
 */
public class ConsumeMessage implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            System.err.println("message:" + (new String(message.getBody(),"utf-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
