package com.spring.cache.api;

import com.spring.api.SpringContextHolder;
import com.spring.api.bean.Bean1;
import com.spring.api.eventlistener.MyEvent;
import com.spring.api.eventlistener.MyListener;
import org.junit.Test;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.bean.BaseTest;

import java.util.Arrays;

/**
 * fuquanemail@gmail.com
 * 2015/9/30 15:27
 */
public class APITest extends BaseTest{

    @Test
    public void getBeanName(){
        ListableBeanFactory beanFactory = SpringContextHolder.applicationContext;
        //String [] names = beanFactory.getBeanDefinitionNames(); //所有的beanName
        String [] names = beanFactory.getBeanNamesForType(Bean1.class);
        System.out.println(Arrays.toString(names));


        //org.springframework.beans.factory.ListableBeanFactory.getBeanDefinitionNames(getbean.getClass())

        //SpringContextHolder.applicationContext.getBean("com.spring.api.bean.Bean1");
    }

    @Test
    public void eventListener1(){
        MyEvent myEvent = new MyEvent("测试event");
        System.out.println("=====开始出发event=======");
        SpringContextHolder.applicationContext.publishEvent(myEvent);
        SpringContextHolder.applicationContext.publishEvent(myEvent);
        SpringContextHolder.applicationContext.publishEvent(myEvent);

        MyListener myListener = SpringContextHolder.getBean("myListener");
        System.out.println(myListener);


    }

    @Test
    public void eventListener2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring.xml");
        MyEvent myEvent = new MyEvent("测试event");
        System.out.println("=====开始出发event=======");

        ac.publishEvent(myEvent);
        ac.publishEvent(myEvent);
        ac.publishEvent(myEvent);
        /*
        事件发生，业务操作, event:MyEvent{eventName='自定义事件'}
        事件发生，业务操作, event:MyEvent{eventName='自定义事件'}
        事件发生，业务操作, event:MyEvent{eventName='自定义事件'}*/
    }

}
