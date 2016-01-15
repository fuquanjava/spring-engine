package spring.rabbitmq;

import org.junit.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * fuquanemail@gmail.com 2016/1/7 14:17
 * description:
 * 1.0.0
 */
public class HelloWorldConfiguration  {
    protected final String helloWorldQueueName = "hello.world.queue";
    @Test
    public void connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        System.err.println(connectionFactory.toString());
    }
    @Test
    public void amqpAdmin() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        //connectionFactory.setUsername("guest");
        //connectionFactory.setPassword("guest");
        // 默认就是 guest/guest
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        System.err.println("AmqpAdmin:"+ rabbitAdmin.declareQueue().getName());
    }

    @Test
    // Every queue is bound to the default direct exchange
    public void helloWorldQueue() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        Queue queue = new Queue(this.helloWorldQueueName);
        System.err.println("queue:" + queue.getName());
    }

    @Test
    public void rabbitTemplate() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        //The routing key is set to the name of the queue by the broker for the default exchange.
        template.setRoutingKey(this.helloWorldQueueName);
        //Where we will synchronously receive messages from
        template.setQueue(this.helloWorldQueueName);

        System.err.println(template.expectedQueueNames().toString());

    }



}
