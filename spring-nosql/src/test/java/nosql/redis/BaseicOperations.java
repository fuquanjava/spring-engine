package nosql.redis;

import nosql.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

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
    public void boundkv(){
        String value= (String) redisTemplate.boundValueOps("a").get();
        logger.info("value = {}", value);

    }



}
