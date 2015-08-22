package util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring-demo 2015/7/24 22:15
 * fuquanemail@gmail.com
 */
public class CoreContextHolder {
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("coreContext.xml");
    public CoreContextHolder() {
    }

    public static ApplicationContext getContext() {
        return ctx;
    }

    public static void setCtx(ApplicationContext ctx) {
        ctx = ctx;
    }

    public static Object getBean(String beanName) {
        return ctx.getBean(beanName);
    }

    public static void closeCtx() {
        ((ClassPathXmlApplicationContext)ctx).close();
    }
}
