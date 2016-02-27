package com.spring.api;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * fuquanemail@gmail.com 2016/2/26 11:19
 * description:
 * 1.0.0
 */
@Component("myBeanNameAware")
public class MyBeanNameAware implements BeanNameAware {

    private String beanName = null;

    public MyBeanNameAware() {
        System.err.println("MyBeanNameAware MyBeanNameAware, 无构造器");
    }

    public MyBeanNameAware(String beanName) {
        System.err.println("MyBeanNameAware MyBeanNameAware, 有构造器");
        this.beanName = beanName;
    }

    @Override
    public void setBeanName(String name) {
        System.err.println("MyBeanNameAware setBeanName, 参数name:" + name);
        this.beanName = name;
    }
    public String getBeanName() {
        return beanName;
    }
}
