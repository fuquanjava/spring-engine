package dao.redis.impl;

import dao.redis.IRedisClientTemplate;
import dao.redis.RedisDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.ShardedJedis;

/**
 * spring-demo 2015/7/19 19:10
 * fuquanemail@gmail.com
 */
public class RedisClientTemplate implements IRedisClientTemplate {
    private static final Logger log = LoggerFactory.getLogger(RedisClientTemplate.class);

    @Autowired
    private RedisDataSource redisDataSource;


    @Override
    public void disconnect() {
        redisDataSource.returnResource(redisDataSource.getRedisClient());
    }

    @Override
    public String set(String key, String value) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
            result = shardedJedis.set(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            //需要手动回收链接
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    @Override
    public String get(String key) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
            result = shardedJedis.get(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }
}
