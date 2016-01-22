package rabbitmq.spring.ext;

import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

import java.io.UnsupportedEncodingException;

/**
 * fuquanemail@gmail.com 2016/1/21 15:38
 * description:
 * 1.0.0
 */
public class MyMessageConverter extends AbstractMessageConverter {



    @Override
    protected Message createMessage(Object object, MessageProperties messageProperties) {
        System.err.println("MyMessageConverter createMessage 发送消息");
        Gson gson = new Gson();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        byte [] bytes = null;
        try {
            bytes = gson.toJson(object).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new Message(bytes, messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        Gson gson = new Gson();
        String jsonMessage = gson.toJson(message);
        System.err.println("MyMessageConverter fromMessage :" + jsonMessage);

        return jsonMessage;
    }
}
