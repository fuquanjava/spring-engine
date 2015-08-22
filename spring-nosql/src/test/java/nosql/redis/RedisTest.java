package nosql.redis;

import nosql.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * Spring-demo
 * 2015/8/11 10:56
 */
public class RedisTest extends BaseTest {

    @Autowired
    RedisTemplate redisTemplate;



    @Test
    public void t1(){
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                logger.info(" redisConnection = {}", redisConnection);
                 redisConnection.set(redisTemplate.getKeySerializer().serialize("key"),
                         redisTemplate.getValueSerializer().serialize("1111111111111111111111111"));
                logger.info("=======================================================");
                return null;
            }
        });
    }

    @Test
    public void t2(){
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte [] values = redisConnection.get(redisTemplate.getStringSerializer().serialize("key"));
                logger.info((String) redisTemplate.getValueSerializer().deserialize(values));
                return null;
            }
        });
    }

    @Test
    public void t3(){
        StringRedisTemplate stringRedisTemplate  = new StringRedisTemplate(redisTemplate.getConnectionFactory());
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("str","1");
        logger.info("str = {}", valueOperations.get("str"));
    }

}
