package webapp.controller.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * spring-demo 2015/6/22 11:32
 * fuquanemail@gmail.com
 */
public class HandlerInterceptor1 implements HandlerInterceptor {

    static Logger logger = LoggerFactory.getLogger(HandlerInterceptor1.class);
    // 我们的拦截器是单例， 因此不管用户请求多少次都只有一个拦截器实现， 即线程不安全， 那我们应该怎么记录时间呢？
    static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("HandlerInterceptor1 ...  [ preHandle ] , handler = [{}]" , handler.toString());
        threadLocal.set(System.nanoTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("HandlerInterceptor1 ...  [ postHandle ] , handler = [{}] , modelAndView  = [{}]" , handler.toString() , modelAndView.toString());

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = threadLocal.get();
        long entTime = System.nanoTime();
        logger.info("HandlerInterceptor1 ...  [ preHandle ] , 耗时，单位纳秒 = [{}]" , entTime - startTime);


    }
}
