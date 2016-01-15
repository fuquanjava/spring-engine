package spring.rabbitmq.helloMq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * fuquanemail@gmail.com 2016/1/7 19:45
 * description:
 * 1.0.0
 */
public class MqProducer {
    public static void main(String[] args) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1");
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setQueue("mq1");
        //template.setRoutingKey(this.helloWorldQueueName);
        int k = 0;
        while (k < 100) {

            template.convertAndSend("Hello World" + k);
            System.out.println("Sent: Hello World" + k);

            k++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
