package rabbitmq.rpc;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import rabbitmq.RabbitUtil;

import java.io.IOException;

/**
 * spring-engine 2015/12/27 21:39
 * fuquanemail@gmail.com
 */
public class RpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Channel channel = RabbitUtil.getChannel();
        AMQP.Queue.DeclareOk declareOk =
                channel.queueDeclare("rpc_queue", false, false, false, null);
        System.err.println("declareOk:" + declareOk);

        //可能要运行多个服务器进程，为了传播同样的负载在多个服务器上，
        // 我们需要通过channel.basicQos来设置prefetchcount
        channel.basicQos(1);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        String str = channel.basicConsume("rpc_queue", false, consumer);
        System.err.println("Awaiting RPC requests : " + str);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();

            AMQP.BasicProperties props = delivery.getProperties();
            //从响应队列获取reply参数
            AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                    .Builder()
                    .correlationId(props.getCorrelationId())
                    .build();

            String message = new String(delivery.getBody());
            int n = Integer.parseInt(message);

            System.out.println(" [.] fib(" + message + ")");
            String response = "" + fib(n);
            Thread.sleep(3000); //模仿耗时操作

            //将结果返回给客户端
            channel.basicPublish( "", props.getReplyTo(), replyProps, response.getBytes());
            //设置确认消息
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }

    }

    private static int fib(int n)  {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib(n-1) + fib(n-2);
    }


}
