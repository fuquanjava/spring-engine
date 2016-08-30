package spring.mq.rabbitmq.exchange;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * fuquanemail@gmail.com 2016/8/30 17:20
 * description:
 */
public class FanoutExchangeTest {
    FanoutExchange fanoutExchange = null;

    @Before
    public void setUp() throws Exception {
        fanoutExchange = new FanoutExchange();
    }

    @Test
    public void testSender() throws Exception {
        new Thread() {
            @Override
            public void run() {
                try {
                    fanoutExchange.sender();
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
                    fanoutExchange.consumer("c1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    fanoutExchange.consumer("c2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Thread.currentThread().join();
    }
}