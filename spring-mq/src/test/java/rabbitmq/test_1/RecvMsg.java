package rabbitmq.test_1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * fuquanemail@gmail.com 2015/12/15 13:40
 * description:
 * 1.0.0
 */
public class RecvMsg {


    //队列名称
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws java.io.IOException,
            java.lang.InterruptedException {
        //打开连接和创建频道，与发送端一样
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        Channel channel = connection.createChannel();
        //声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        /// 可选：指定预取个数。默认rabbit将队列中的消息均分成n份分发给n个consumer，而不管consumer有多少消息是否被ack。
        // 指定预取为1了表示rabbit一次只需要推一条消息，直到该消息ack了一个再推下一条
        channel.basicQos(1);


        //创建队列消费者
//        QueueingConsumer里面会创建一个缓存队列(LinkedBlockingQueue , 默认是 int的最大值)用于保存服务器推过来的消息，这样可以异步的实现推送和消费，
//    当然你可以在构造函数中自己传入用于buffer的queue
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //指定消费队列  basicConsume(队列名字, 是否自动应答, 消费者队列);
//        channel.basicConsume(QUEUE_NAME, true, consumer);
        channel.basicConsume(QUEUE_NAME, false, consumer);
        while (true) {
            Thread.sleep(1000);
            //nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            boolean result = doWork(message);
            if( result){
                //发送应答
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                // POISON 毒丸对象，用毒丸对象来标记是否shutdown or cancel

                //delivery 传输

                //Envelope 信封
            }
            //System.out.println(" [x] Received '" + message + "'");
        }

    }

    private static boolean doWork(String message) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        int r = random.nextInt(100);

        if(r % 2 == 0){
            System.out.println("message do work done :"+ message);
            return true;
        }else {
            System.err.println("message execute fail : " + message);
            return false;
        }

    }
}
