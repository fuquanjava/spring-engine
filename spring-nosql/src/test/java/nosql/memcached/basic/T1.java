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
public class T1 extends BaseTest {

    @Autowired
    MemcachedClient memcachedClient;
    @Test
    public void t1(){
        String name = memcachedClient.getName();
        logger.info("name=[{}]", name);

        try {
            memcachedClient.set("hello",100, "helloworld");

            String value = memcachedClient.get("hello");
            logger.info("value=[{}]", value);

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }
}
