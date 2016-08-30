package spring.mq.rabbitmq;

import org.junit.Before;
import org.junit.Test;
import spring.mq.rabbitmq.routing.DirectExchange;

/**
 * fuquanemail@gmail.com
 */
public class RoutingTest {


    DirectExchange directExchange = null;

    @Before
    public void setUp() throws Exception {
        directExchange = new DirectExchange();
    }

    @Test
    public void testDirectExchange() throws Exception {
        directExchange.sender();
    }

    @Test
    public void test_consumer() throws Exception {
        directExchange.consumer();
    }

}
