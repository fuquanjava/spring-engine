package rabbitmq.transactional;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQImpl;
import rabbitmq.RabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * fuquanemail@gmail.com 2015/12/28 9:30
 * description: pusher的事务机制
 * 1.0.0
 */
public class TransactionalSender {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitUtil.getChannel();

        AMQImpl.Tx.SelectOk selectOk = (AMQImpl.Tx.SelectOk) channel.txSelect();
        System.err.println("selectOk:" + selectOk);

        channel.exchangeDeclare("tx_queue", "fanout");

        channel.basicPublish("tx_queue", "", null, "hello".getBytes());

        AMQImpl.Tx.CommitOk commitOk = (AMQImpl.Tx.CommitOk) channel.txCommit();

        System.err.println("commitOk : " + commitOk);

        // 回滚
        //AMQImpl.Tx.RollbackOk rollbackOk = (AMQImpl.Tx.RollbackOk) channel.txRollback();
        //System.err.println("rollbackOk : " + rollbackOk);

        channel.close();
    }
}
