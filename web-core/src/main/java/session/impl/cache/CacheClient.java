package session.impl.cache;

import session.impl.CacheHttpSession;

import javax.servlet.http.HttpSession;

/**
 * spring-demo 2015/7/23 22:40
 * fuquanemail@gmail.com
 */
public interface CacheClient {
    CacheHttpSession loadCacheSession(String sessionId);
    void saveSessionToCache(String sessionId, HttpSession session, long liveTime) ;
    void removeSessionFromCache(String sessionId) ;

}
