package rabbitmq.transactional;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import rabbitmq.RabbitUtil;

import java.io.IOException;

/**
 * fuquanemail@gmail.com 2015/12/28 9:39
 * description:
 * 1.0.0
 */
public class ConfirmSender2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Channel channel = RabbitUtil.getChannel();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("handleAck :" + deliveryTag+","+ multiple);
            }
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("handleNack :" + deliveryTag+","+ multiple);
            }
        });
        channel.confirmSelect();


        channel.exchangeDeclare("immediate_queue", "direct");

        channel.basicPublish("immediate_queue","",true,false,null,"helloo1".getBytes() );
        channel.basicPublish("immediate_queue","",true,false,null,"helloo2".getBytes() );
        channel.basicPublish("immediate_queue","",true,false,null,"helloo3".getBytes() );
        channel.basicPublish("immediate_queue","",true,false,null,"helloo4".getBytes() );

        //        等待时间
        //        channel.waitForConfirms(1000);
        // 一直阻塞等待.
        boolean confirmed = channel.waitForConfirms();
        if(confirmed){
            System.err.println("消息发送成功");
        }else{
            System.err.println("消息发送失败");
        }

    }
}
