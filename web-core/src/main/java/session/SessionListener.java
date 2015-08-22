package session;

import session.impl.CacheHttpSession;

/**
 * spring-demo 2015/7/22 21:04
 * fuquanemail@gmail.com
 */
public interface SessionListener {

    void onAttributeChanged(CacheHttpSession var1);

    void onInvalidated(CacheHttpSession var1);
}
