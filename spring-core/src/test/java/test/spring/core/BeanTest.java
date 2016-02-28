package test.spring.core;

import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.core.bean.HelloBean;

/**
 * fuquanemail@gmail.com 2016/2/26 17:30
 * description:
 * 1.0.0
 */
public class BeanTest {

    @Test
    public void HelloBean() {
        AbstractXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");
        HelloBean helloBean = (HelloBean) context.getBean("helloBean");
        System.err.println("helloBean:" + helloBean);
    }
}
