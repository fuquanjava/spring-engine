package com.spring.api.bean;

import org.springframework.beans.factory.BeanNameAware;

/**
 * fuquanemail@gmail.com
 * 2015/10/9 16:39
 */

public class Bean4 implements BeanNameAware {
    private String beanName;
    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
