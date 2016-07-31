package nosql.redis.jedis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * fuquanemail@gmail.com
 * JedisShardInfo: 是 ShardedJedis 的基本连接信息, eg: host, port , timeout, password.
 * ShardedJedis : 是基于一致性哈希算法实现的分布式Redis集群客户端；
 */
public class ShardedJedisTest {

    ShardedJedis jedis = null;

    ShardedJedisPool jedisPool = null;

    @Before
    public void init() {
        JedisShardInfo node1 = new JedisShardInfo("192.168.1.133", 7000);
        //JedisShardInfo node1slave = new JedisShardInfo("192.168.1.133", 7001);
        JedisShardInfo node2 = new JedisShardInfo("192.168.1.133", 7002);
        //JedisShardInfo node2slave = new JedisShardInfo("192.168.1.133", 7003);

        List<JedisShardInfo> shards = new ArrayList<>();
        shards.add(node1);
        //shards.add(node2slave);
        shards.add(node2);
        //shards.add(node1slave);

        jedis = new ShardedJedis(shards);

    }

    @Test
    public void set() {
        String ok = jedis.set("a", "zs");
        System.out.println("is ok?" + ok);

        String value = jedis.get("a");
        System.out.println("value:" + value);
    }


    public void shardedJedisPool() {
        // ShardedJedisPool (final GenericObjectPoolConfig poolConfig, List<JedisShardInfo> shards)


        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(2);
        config.setMaxTotal(5);
        config.setMaxWaitMillis(60000);
        config.setTestOnBorrow(true);
        config.setTestOnCreate(true);

        JedisShardInfo node1 = new JedisShardInfo("192.168.1.133", 7000);
        JedisShardInfo node2 = new JedisShardInfo("192.168.1.133", 7002);

        List<JedisShardInfo> shards = new ArrayList<>();
        shards.add(node1);
        shards.add(node2);


        jedisPool = new ShardedJedisPool(config, shards);


    }

    @Test
    public void t2(){
        shardedJedisPool();

        ShardedJedis jedis1 = jedisPool.getResource();
        ShardedJedis jedis2 = jedisPool.getResource();

        Provider p = new Provider(jedis1);
        Consumer c = new Consumer(jedis2);
        p.start();
        c.start();

        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void t1() {

        JedisShardInfo node1 = new JedisShardInfo("192.168.1.133", 7000);
        //JedisShardInfo node1slave = new JedisShardInfo("192.168.1.133", 7001);
        JedisShardInfo node2 = new JedisShardInfo("192.168.1.133", 7002);
        //JedisShardInfo node2slave = new JedisShardInfo("192.168.1.133", 7003);

        List<JedisShardInfo> shards = new ArrayList<>();
        shards.add(node1);
        //shards.add(node2slave);
        shards.add(node2);
        //shards.add(node1slave);

        ShardedJedis jedis1 = new ShardedJedis(shards);

        ShardedJedis jedis2 = new ShardedJedis(shards);


        Provider p = new Provider(jedis1);
        Consumer c = new Consumer(jedis2);
        p.start();
        c.start();

        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    static class Provider extends Thread {
        ShardedJedis jedis;

        public Provider(ShardedJedis jedis) {
            this.jedis = jedis;
        }

        @Override
        public void run() {
            int i = 1000;
            while (true) {
                String ok = jedis.set(i + "", i + "");
                System.out.println("set " + i + " ok ? " + ok);
                i++;
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    static class Consumer extends Thread {
        ShardedJedis jedis;

        public Consumer(ShardedJedis jedis) {
            this.jedis = jedis;
        }

        @Override
        public void run() {
            int i = 1000;
            while (true) {
                String value = jedis.get(i + "");
                System.err.println("get " + i + " value = " + value);
                i++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
