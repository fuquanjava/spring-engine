package rabbitmq.spring.ext;

import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;

/**
 * fuquanemail@gmail.com 2016/1/15 17:11
 * description:
 * 1.0.0
 */
public class MyMessageListenerContainer extends AbstractMessageListenerContainer {
    @Override
    protected void doInitialize() throws Exception {

    }

    @Override
    protected void doShutdown() {

    }
}
