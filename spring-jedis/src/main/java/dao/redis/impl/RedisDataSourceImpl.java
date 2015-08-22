package dao.redis.impl;

import dao.redis.RedisDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * spring-demo 2015/7/19 16:10
 * fuquanemail@gmail.com
 */
@Repository("redisDataSource")
public class RedisDataSourceImpl implements RedisDataSource {
    static final Logger LOG = LoggerFactory.getLogger(RedisDataSourceImpl.class);

    @Autowired
    ShardedJedisPool shardedJedisPool;

    public ShardedJedis getRedisClient() {
        try {
            ShardedJedis shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
            LOG.error("getRedisClent error", e);
        }
        return null;
    }

    @Override
    public void returnResource(ShardedJedis resource) {
        shardedJedisPool.returnResourceObject(resource);
    }
}
