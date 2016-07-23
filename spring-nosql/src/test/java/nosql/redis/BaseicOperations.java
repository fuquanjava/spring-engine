package nosql.redis;

import dao.User;
import nosql.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * Spring-demo
 * 2015/8/13 10:08
 */
public class BaseicOperations extends BaseTest{

    @Autowired
    RedisTemplate redisTemplate;


    @Before
    public void setUp(){

    }
    @After
    public void tearDown(){
        logger.info("down ... down .. down .");

    }
    @Test
    public void testConnection(){
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(redisTemplate.getKeySerializer().serialize("a"),redisTemplate.getValueSerializer().serialize("123"));
                byte [] bytes = connection.get(redisTemplate.getKeySerializer().serialize("a"));
                Object value = redisTemplate.getValueSerializer().deserialize(bytes);
                System.out.println("value , "+ value);
                return true;
            }
        });

    }

    @Test
    public void getSerializer(){
        String keySerializer = redisTemplate.getKeySerializer().getClass().getSimpleName();
        String valueSerializer = redisTemplate.getValueSerializer().getClass().getSimpleName();
        String defaultSerializer = redisTemplate.getDefaultSerializer().getClass().getSimpleName();

        logger.info("keySerializer = {}" , keySerializer);
        logger.info("valueSerializer = {}" , valueSerializer);
        logger.info("defaultSerializer = {}" , defaultSerializer);

    }
    @Test
    public void testkv(){
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("a", "b");
        String value = (String) valueOperations.get("a");
        logger.info("value  = [{}]" , value);
        // 注意 ValueOperations 是redisTemplate的属性，并且是单例
        // <String,Object> 的K,V 的类型是RedisTemplate的类型，这里的K,V允许什么值需要参照 Redis的配置
        User u1 = new User(1, "tom1");
        valueOperations.set("1", u1);
        logger.info("user = {}", valueOperations.get("1"));

        boolean exists = valueOperations.setIfAbsent("a","b");
        logger.info("a 是否存在: {}", exists);

        logger.info("---------------------save----------------------------");

        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte [] key = redisTemplate.getKeySerializer().serialize("b");
                byte [] value = redisTemplate.getValueSerializer().serialize("bbb");
                connection.set(key, value);
                return null;
            }
        });

        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte []  key = redisTemplate.getKeySerializer().serialize("b");
                byte [] value = connection.get(key);
                String s  = (String) redisTemplate.getValueSerializer().deserialize(value);
                logger.info(" value = {}" , s);
                return null;
            }
        });

    }

    @Test
    public void boundkv(){
        String value= (String) redisTemplate.boundValueOps("a").get();
        logger.info("value = {}", value);

    }



}
