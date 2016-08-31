package spring.mq.rabbitmq.api;

import org.junit.Before;
import org.junit.Test;

/**
 * fuquanemail@gmail.com
 */
public class RabbitMQApiTest {

    RabbitMQApi api = null;

    @Before
    public void setUp() throws Exception {
        api = new RabbitMQApi();

    }

    @Test
    public void sendOne() throws Exception {
        api.sendOne();
    }

    @Test
    public void send() throws Exception {
        api.send();
    }

    @Test
    public void receiveAutoAck() throws Exception {


        new Thread(){
            @Override
            public void run() {
                try {
                    api.receiveAutoAck("c1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    api.receiveAutoAck("c2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Thread.currentThread().join();
    }

    @Test
    public void receiveAck() throws Exception {


        new Thread(){
            @Override
            public void run() {
                try {
                    api.receiveAck("c1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    api.receiveAck("c2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Thread.currentThread().join();
    }
}