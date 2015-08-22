package dao.redis;

import redis.clients.jedis.ShardedJedis;

/**
 * spring-demo 2015/7/19 16:09
 * fuquanemail@gmail.com
 */
public interface RedisDataSource {

    /**
     * 获取客户端
     * @return
     */
    public abstract ShardedJedis getRedisClient();

    /**
     * 返回连接到连接池
     * @param resource
     */
    public abstract void returnResource(ShardedJedis resource) ;
}
