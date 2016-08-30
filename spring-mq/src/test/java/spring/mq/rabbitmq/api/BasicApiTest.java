package spring.mq.rabbitmq.api;

import org.junit.Before;
import org.junit.Test;

/**
 * fuquanemail@gmail.com
 */
public class BasicApiTest {

    BasicApi api = null;

    @Before
    public void setUp() throws Exception {
        api = new BasicApi();

    }

    @Test
    public void testSend() throws Exception {
        api.send();
    }

    @Test
    public void testReceive() throws Exception {
        api.receive();

        new Thread(){
            @Override
            public void run() {
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Thread.currentThread().join();
    }
}