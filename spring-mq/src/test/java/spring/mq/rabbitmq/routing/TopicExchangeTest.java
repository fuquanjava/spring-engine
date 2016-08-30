package spring.mq.rabbitmq.routing;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * fuquanemail@gmail.com 2016/8/30 17:20
 * description:
 */
public class TopicExchangeTest {

    TopicExchange topicExchange = null;

    @Before
    public void setUp() throws Exception {
        topicExchange = new TopicExchange();
    }

    @Test
    public void testSender() throws Exception {
        new Thread() {
            @Override
            public void run() {
                try {
                    topicExchange.sender();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Thread.currentThread().join();
    }

    @Test
    public void testConsumer() throws Exception {
        new Thread() {
            @Override
            public void run() {
                try {
                    topicExchange.consumer("c1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    topicExchange.consumer("c2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Thread.currentThread().join();
    }
}