package web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * spring-demo 2015/6/22 11:43
 * fuquanemail@gmail.com
 */
public class HandlerInterceptor2 extends HandlerInterceptorAdapter {
    static Logger logger = LoggerFactory.getLogger(HandlerInterceptor2.class);


    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("HandlerInterceptor2 ...  [ preHandle ] ", handler.toString());

    }

}
