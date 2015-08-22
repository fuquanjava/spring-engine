package session.impl.cache;

import session.impl.CacheHttpSession;
import util.RedisClient;

import javax.servlet.http.HttpSession;

/**
 * spring-demo 2015/7/25 16:32
 * fuquanemail@gmail.com
 */
public class RedisCacheImpl implements CacheClient {
    RedisClient redisClient;

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    @Override
    public CacheHttpSession loadCacheSession(String sessionId) {
        return (CacheHttpSession) redisClient.getObject(sessionId);
    }

    @Override
    public void saveSessionToCache(String sessionId, HttpSession session, long liveTime) {
        redisClient.addItem(sessionId, session);
    }

    @Override
    public void removeSessionFromCache(String sessionId) {
        redisClient.del(sessionId);
    }
}
