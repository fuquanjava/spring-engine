package spring.xml;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;

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
            byte [] bytes = message.getBody();
            String msg = new String(bytes,"UTF-8");
            System.out.println("msg:" + msg);

            MessageProperties messageProperties = message.getMessageProperties();
            System.err.println("messageProperties" + messageProperties);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
