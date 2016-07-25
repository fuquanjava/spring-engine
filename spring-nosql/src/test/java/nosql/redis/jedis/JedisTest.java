package nosql.redis.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * fuquanemail@gmail.com
 */
public class JedisTest {
    Jedis jedis = null;


    public void connnection4Test(String node) {
        if("node1".equals(node)) {
            jedis = new Jedis("192.168.1.128",6379);
        }
        if("node2".equals(node)) {
            jedis = new Jedis("192.168.1.128", 6380);
        }
        String pong = jedis.ping();
        System.out.println(pong);
    }

    @Test
    public void set(){
        // if connection node2 ,will throw READONLY: JedisDataException You can't write against a read only slave.

        connnection4Test("node2");

        jedis.set("a", "123");
        System.out.println(jedis.get("a"));
    }
    @Test
    public void get(){
        connnection4Test("node2");
        System.out.println(jedis.get("a"));

        System.out.println(jedis.dbSize());
    }
}
