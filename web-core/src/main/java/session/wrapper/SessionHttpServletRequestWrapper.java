package session.wrapper;
import session.impl.CacheHttpSession;
import session.impl.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * spring-demo 2015/7/22 21:21
 * fuquanemail@gmail.com
 * 封装 HttpServletRequest
 */
public class SessionHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletResponse response;
    private CacheHttpSession httpSession;
    private SessionManager sessionManager;

    public SessionHttpServletRequestWrapper(HttpServletRequest request,
         HttpServletResponse response, SessionManager sessionManager) {
        super(request);
        this.response = response;
        this.sessionManager = sessionManager;
    }
    public HttpSession getSession(boolean create){
        if(this.httpSession != null && ! this.httpSession.expired){
            return this.httpSession;
        }else {
            this.httpSession = this.sessionManager.createSession(this, this.response, create);
            return this.httpSession;
        }
    }
    public HttpSession getSession() {
        return this.getSession(true);
    }

}
