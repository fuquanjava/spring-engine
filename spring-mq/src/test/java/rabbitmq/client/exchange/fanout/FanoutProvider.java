package rabbitmq.client.exchange.fanout;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import rabbitmq.client.C;
import rabbitmq.client.RabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * spring-engine 2015/12/26 16:53
 * fuquanemail@gmail.com
 */
public class FanoutProvider {
    public static void main(String[] args) {
        Channel channel = RabbitUtil.getChannel();
        try {
            //创建一个fanout类型的交换机
            AMQP.Exchange.DeclareOk declareOk =
                    channel.exchangeDeclare(C.FANOUT_EXCHANGE,"fanout");
            System.err.println("declareOk: " +declareOk);

            int i = 0;
            while (i< 100){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i ++;
                String msg = "hello , "+ i;
                channel.basicPublish(C.FANOUT_EXCHANGE,"", null, msg.getBytes());
                System.err.println(Thread.currentThread().getName()+" send msg : " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }


    }


}
