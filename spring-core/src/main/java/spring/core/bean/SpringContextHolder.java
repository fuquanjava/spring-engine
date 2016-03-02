package spring.core.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * fuquanemail@gmail.com
 * 2015/9/30 15:15
 */
@Component("springContextHolder")
public class SpringContextHolder implements ApplicationContextAware {
    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }


    public static <T> T getBean (String name){
        return (T) applicationContext.getBean(name);
    }
}
