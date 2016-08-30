package spring.mq.rabbitmq.exchange;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * fuquanemail@gmail.com 2016/8/30 16:17
 * description:
 */
public class DirectExchangeTest {
    DirectExchange directExchange = null;

    @Before
    public void setUp() throws Exception {
        directExchange = new DirectExchange();
    }

    @Test
    public void testSender() throws Exception {
        new Thread() {
            @Override
            public void run() {
                try {
                    directExchange.sender();
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
                    directExchange.consumer("c1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    directExchange.consumer("c2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Thread.currentThread().join();
    }
}