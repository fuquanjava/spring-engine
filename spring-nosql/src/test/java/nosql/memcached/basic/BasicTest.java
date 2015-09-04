package nosql.memcached.basic;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import nosql.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeoutException;

/**
 * spring-engine
 * 2015/9/1 16:58
 */
public class BasicTest extends BaseTest {

    @Autowired
    MemcachedClient client;

    @Test
    public void t1(){
        try {
            //直接覆盖原来的值
            boolean setOk = client.set("1",1,"a");
            logger.info("==============================setOK = {}", setOk);

            boolean addOk = client.add("1",1,"a");
            logger.info("==============================addOk = {}", addOk);

            boolean replaceOk = client.replace("1",1,"b");
            logger.info("==============================replaceOk = {}", replaceOk);

            boolean appendOk = client.append("1","cc");
//            client.appendWithNoReply();
            logger.info("==============================appendOk = {}", appendOk);

            //Memcached prepend 命令用于向已存在 key(键) 的 value(数据值) 前面追加数据 。
            boolean prependOk = client.prepend("1","aa");
            logger.info("==============================prependOk = {}", prependOk);


            client.flushAll();


        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }

    }
}
