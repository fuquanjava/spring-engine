package org.springframework.bean;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author: fuquanemail@gmail.com 2015/10/23 11:07
 * description:
 * @version: 1.0.0
 */
public class ApplicationContextAPITest {

    ClassPathXmlApplicationContext context = null;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("classpath:spring.xml");
    }

    @Test
    public void t1(){
        /*根据名称返回一个Bean，客户端需要自己进行类型转换*/
        HelloBean helloBean = (HelloBean) context.getBean("bean1");

       /* 根据名称和指定的类型返回一个Bean，客户端无需自己进行类型
        转换，如果类型转换失败，容器抛出异常；*/
        HelloBean bean2 = context.getBean("bean1", HelloBean.class);


        /*根据指定的类型返回一个Bean，客户端无需自己进行类型转换，如果没有或有
                多于一个Bean存在容器将抛出异常*/

        //异常: org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type [spring.bean.HelloBean] is defined: expected single matching bean but found 4: bean1,bean3,bean2,bean4
        HelloBean bean3 = context.getBean(HelloBean.class);


        /*根据指定的类型返回一个键值为名字和值为Bean对象的 Map，
        如果没有Bean对象存在则返回空的Map*/
        Map<String, HelloBean> beanMap = context.getBeansOfType(HelloBean.class);
        System.err.println(beanMap);
    }

}
