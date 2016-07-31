package nosql.redis.jedis;


import com.alibaba.fastjson.JSON;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * fuquanemail@gmail.com
 * <p>
 * JedisPool: 连接池的管理（创建，回收）, 继承 commons-pool2 实现
 * <p>
 * JedisPoolConfig: JedisPool连接池的配置属性，非线程安全的
 * <p>
 * PooledObjectFactory: JedisPool的监听器，监听Jedis实例的动作，要保证线程安全。
 */
public class JedisPoolTest {


    /**
     * jedisPoolConfig 连接池的配置
     * <p>
     * JedisPoolConfig extends org.apache.commons.pool2.impl.GenericObjectPoolConfig
     * GenericObjectPoolConfig extends BaseObjectPoolConfig
     * abstract class BaseObjectPoolConfig implements Cloneable
     * <p>
     * jedisPoolConfig　继承了　commons.pool2.
     * <p>
     * GenericObjectPoolConfig:
     * This class is not thread-safe; it is only intended to be used to provide ,
     * attributes used when creating a pool.
     */
    @Test
    public void jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(2);
        config.setMaxTotal(5);
        config.setMaxWaitMillis(60000);
        config.setTestOnBorrow(true);
        config.setTestOnCreate(true);

        System.out.println(JSON.toJSONString(config, true));
    }

    /**
     * JedisPool extends Pool<Jedis>
     * abstract class Pool<T> implements Closeable {
     * protected GenericObjectPool<T> internalPool;
     * }
     * <p>
     * 所以 jedisPoolConfig是 JedisPool的属性。
     */
    @Test
    public void jedisPool() {
        JedisPool pool = new JedisPool("192.168.1.128", 7002);
        System.out.println(pool.getNumActive());

        Jedis jedis = pool.getResource();

        System.out.println(jedis);

        String ok = jedis.set("a", "1");
        System.out.println("set ok ? " + ok);

        String value = jedis.get("a");
        System.out.println("get value:" + value);

        jedis.close(); // 会返回资源给 pool

        pool.close();


    }

    @Test
    public void connection() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(2);
        config.setMaxTotal(5);
        config.setMaxWaitMillis(60000);
        config.setTestOnBorrow(true);
        config.setTestOnCreate(true);

        JedisPool pool = new JedisPool(config, "192.168.1.128", 7001);

        Jedis jedis = pool.getResource();

        String ok = jedis.set("a", "1");
        System.out.println("set ok ? " + ok);

        String value = jedis.get("a");
        System.out.println("get value:" + value);
    }

    @Test
    public void crashingJedisPooledObjectFactory() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(1);

        JedisPool pool = new JedisPool(config, "192.168.1.128", 7000, 2000, "foobared");

        // 要保证 PooledObjectFactory 的线程安全，所以 new 一个实例
        pool.initPool(config, new CrashingJedisPooledObjectFactory());

        Jedis crashingJedis = pool.getResource();

        try {
            crashingJedis.close();
        } catch (Exception ignored) {

        }

    }

    static class CrashingJedisPooledObjectFactory implements PooledObjectFactory<Jedis> {

        @Override
        public PooledObject<Jedis> makeObject() throws Exception {
            return new DefaultPooledObject<Jedis>(new CrashingJedis());
        }

        @Override
        public void destroyObject(PooledObject<Jedis> p) throws Exception {
            destroyed.incrementAndGet();
        }

        @Override
        public boolean validateObject(PooledObject<Jedis> p) {
            return true;
        }

        @Override
        public void activateObject(PooledObject<Jedis> p) throws Exception {
        }

        @Override
        public void passivateObject(PooledObject<Jedis> p) throws Exception {
        }
    }

    static class CrashingJedis extends Jedis {
        @Override
        public void resetState() {
            throw new RuntimeException();
        }
    }

    static AtomicInteger destroyed = new AtomicInteger(0);

}
