package redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import redis.clients.jedis.Jedis;
import session.impl.cache.CacheClient;

/**
 * spring-demo 2015/7/25 11:23
 * fuquanemail@gmail.com
 */
public class RedisConn {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.184.129");
        jedis.connect();
        String ping = jedis.ping();
        System.out.println(ping);
//        connBySpring();

        testCoreContext();
    }

    private static void testCoreContext() {
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"coreContext.xml"});
        CacheClient c = (CacheClient) ac.getBean("cacheClient");
        System.out.println(c.loadCacheSession("1"));


    }

    private static void connBySpring() {
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"coreContext.xml"});
        JedisConnectionFactory factory = (JedisConnectionFactory) ac.getBean("connectionFactory");
        System.out.println(factory.getConnection().isClosed());

        /*final RedisTemplate r = (RedisTemplate) ac.getBean("redisTemplate");
        r.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                //connection.set(r.getStringSerializer().serialize("1"), r.getValueSerializer().serialize(1));
                System.out.println(connection.isClosed());
                return null;
            }
        });
        System.out.println(r);*/

    }


}
class MyRedisCallback implements RedisCallback{
    @Override
    public Object doInRedis(RedisConnection connection) throws DataAccessException {
        return null;
    }
}