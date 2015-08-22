package session.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import session.impl.cache.CacheClient;
import session.wrapper.SessionHttpServletRequestWrapper;

import javax.servlet.http.*;
import java.util.UUID;

/**
 * spring-demo 2015/7/19 21:35
 * fuquanemail@gmail.com
 * 管理session
 */
public class SessionManager {
    final static Logger LOGGER = LoggerFactory.getLogger(SessionManager.class);
    private static final String SESSION_ID_COOKIE = "WEB_JSESSIONID";

    private CacheClient cacheClient;

    private int maxInactiveInterval = 1800;
    private String domain = "";

    public void setCacheClient(CacheClient cacheClient) {
        this.cacheClient = cacheClient;
    }

    public CacheHttpSession createSession(SessionHttpServletRequestWrapper requestWrapper,
                                          HttpServletResponse response, boolean create) {
        String sessionId = this.getRequestedSessionId(requestWrapper);
        CacheHttpSession session = null;
        if(StringUtils.isBlank(sessionId) &&  !create){
            return null;
        } else {
            if(StringUtils.isNotEmpty(sessionId)) {
                session = this.loadCacheSession(sessionId);
            }

            if(session == null && create) {
                session = this.createEmptySession(requestWrapper, response);
            }
            if(session !=  null){
                saveCookie(session, requestWrapper , response);
                cacheClient.saveSessionToCache(sessionId, session, 10000);
            }
            return session;
        }
    }


    private CacheHttpSession createEmptySession(SessionHttpServletRequestWrapper request, HttpServletResponse response) {
        CacheHttpSession session = new CacheHttpSession();
        session.id = this.createSessionId();
        session.creationTime = System.currentTimeMillis();
        session.maxInactiveInterval = this.maxInactiveInterval;
        session.isNew = true;

        this.saveCookie(session, request, response);
        return session;
    }
    private String createSessionId() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
    private void addCookie(CacheHttpSession session, HttpServletRequestWrapper request, HttpServletResponse response) {
        Cookie cookie = new Cookie(SESSION_ID_COOKIE, (String)null);
        if(!StringUtils.isBlank(this.domain)) {
            cookie.setDomain(this.domain);
        }

        cookie.setPath(StringUtils.isBlank(request.getContextPath())?"/":request.getContextPath());
        if(session.expired) {
            cookie.setMaxAge(0);
        } else if(session.isNew) {
            cookie.setValue(session.getId());
        }

        response.addCookie(cookie);
    }

    public void addCookie(HttpServletRequest request, HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        if(!StringUtils.isBlank(this.domain)) {
            cookie.setDomain(this.domain);
        }

        cookie.setPath(StringUtils.isBlank(request.getContextPath())?"/":request.getContextPath());
        response.addCookie(cookie);
    }

    private void saveCookie(CacheHttpSession session, HttpServletRequestWrapper request, HttpServletResponse response) {
        if(session.isNew || session.expired) {
            Cookie[] cookies = request.getCookies();
            if(cookies != null && cookies.length != 0) {
                Cookie[] arr$ = cookies;
                int len$ = cookies.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    Cookie cookie = arr$[i$];
                    if(SESSION_ID_COOKIE.equals(cookie.getName())) {
                        if(!StringUtils.isBlank(this.domain)) {
                            cookie.setDomain(this.domain);
                        }

                        cookie.setPath(StringUtils.isBlank(request.getContextPath())?"/":request.getContextPath());
                        cookie.setMaxAge(0);
                    }
                }

                this.addCookie(session, request, response);
            } else {
                this.addCookie(session, request, response);
            }

            LOGGER.debug("CacheHttpSession saveCookie [ID={}]",session.id);
        }
    }

    /**
     * 从缓存中获取session
     * @param sessionId
     * @return
     */
    private CacheHttpSession loadCacheSession(String sessionId) {
        try {
            HttpSession e = this.getSessionFromCache(this.generatorSessionKey(sessionId));
            CacheHttpSession session;
            if(e == null) {
                LOGGER.debug("session [{}] not found in Redis" , sessionId);
                session = null;
            } else {
                session = (CacheHttpSession)e;
            }

            if(session != null) {
                session.isNew = false;
                session.isDirty = false;
            }

            return session;
        } catch (Exception var4) {
            LOGGER.warn("exception loadSession [Id=" + sessionId + "]", var4);
            return null;
        }
    }

    private String generatorSessionKey(String sessionId) {
        return "R_JSID_".concat(sessionId);
    }

    public HttpSession getSessionFromCache(String id) {
        Object obj = null;
/*        if("true".equalsIgnoreCase(this.twemproxy)) {
            obj = this.cacheClient.loadCacheSession(id);
        } else {
            obj = this.cacheClient.loadCacheSession(this.dbIndex, id);
        }*/
        obj = this.cacheClient.loadCacheSession(id);
        if(obj != null && obj instanceof  HttpSession){
            return (HttpSession)obj;
        }
        return null;
        /*if(obj != null && obj instanceof HttpSession) {
            this.redisDowntime = false;
            return (HttpSession)obj;
        } else if(obj != null && obj instanceof RedisDowntime) {
            this.redisDowntime = true;
            this.log.warn("--------------redis宕机-------------");
            return (HttpSession)this.localSessions.get(id);
        } else {
            this.redisDowntime = false;
            return null;
        }*/
    }

    public void saveSessionToCache(String id, HttpSession session, int liveTime) {
        /*if(this.redisDowntime) {
            this.localSessions.put(id, session);
        } else {
            if("true".equalsIgnoreCase(this.twemproxy)) {
                this.cacheClient.addItem(id, session, liveTime);
            } else {
                this.cacheClient.addItem(this.dbIndex, id, session, liveTime);
            }

            this.localSessions.clear();
        }*/

        this.cacheClient.saveSessionToCache(id ,session, liveTime);

    }

    public void removeSessionFromCache(String id) {
       /* if(this.redisDowntime) {
            this.localSessions.remove(id);
        } else if("true".equalsIgnoreCase(this.twemproxy)) {
            this.cacheClient.delItem(id);
        } else {
            this.cacheClient.delItem(this.dbIndex, id);
        }*/
        this.cacheClient.removeSessionFromCache(id);
    }
    /**
     * 通过cookie 获取 sessionId
     * @param request
     * @return
     */
    private String getRequestedSessionId(HttpServletRequestWrapper request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0){
            for(Cookie cookie : cookies){
                if(SESSION_ID_COOKIE.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }

        }
        return  null;
    }
}
