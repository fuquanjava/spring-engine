package test.spring.core;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
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
        Class<?> clazz = context.getType("helloBean");
        System.err.println(clazz.getSimpleName());
    }

    @Test
    public void beanFactory(){
        ClassPathResource resource = new ClassPathResource("spring-config.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        HelloBean helloBean = (HelloBean) beanFactory.getBean("helloBean");
    }

    @Test
    public void StringTest(){
        String path = "/string";
        path = path.substring(1);
        System.err.println(path);
    }

    @Test
    public void beanNameAware(){
        AbstractXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");
        //MyBeanNameAware beanNameAware = context.getBean(MyBeanNameAware.class);
        //beanNameAware.sayName();
    }

    @Test
    public void applicationListener(){
        AbstractXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");
    }
}
