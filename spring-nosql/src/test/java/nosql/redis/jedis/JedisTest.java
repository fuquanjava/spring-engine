package nosql.redis.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * fuquanemail@gmail.com
 */
public class JedisTest {
    Jedis jedis = null;


    public void connnection4Test(int port) {
        jedis = new Jedis("192.168.1.133", port);
        String pong = jedis.ping();
        System.out.println(pong);
    }

    @Test
    public void set(){
        // if connection node2 ,will throw READONLY: JedisDataException You can't write against a read only slave.

        connnection4Test(7002);

        // if redis is cluster and connection slave :
        // redis.clients.jedis.exceptions.JedisMovedDataException: MOVED 15495 127.0.0.1:7002

        jedis.set("a", "123");
        System.out.println(jedis.get("a"));
    }
    @Test
    public void get(){
        connnection4Test(7002);
        System.out.println(jedis.get("a"));

        System.out.println(jedis.dbSize());
    }

    @Test
    public void setAndGet(){
        connnection4Test(7000);

        String rsp = jedis.set("abc","1");
        System.out.println(rsp);

        String value = jedis.get("abc");
        System.out.println(value);


    }
}
