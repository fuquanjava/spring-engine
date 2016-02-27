package com.spring.api;

import org.junit.Test;

/**
 * fuquanemail@gmail.com 2016/2/26 11:25
 * description:
 * 1.0.0
 */
public class BeanNameAwareTest extends com.spring.BaseTest {

    @Test
    public void t1(){
        MyBeanNameAware myBeanNameAware = (MyBeanNameAware) context.getBean("myBeanNameAware");
    }
}
