package com.dubbo.service.impl;

import com.dubbo.service.HelloService;

/**
 * Spring-Engine 2015/9/20 17:31
 * fuquanemail@gmail.com
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello : "+ name;
    }
}
