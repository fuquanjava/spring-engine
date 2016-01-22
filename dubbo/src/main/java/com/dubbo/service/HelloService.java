package com.dubbo.service;

import com.dubbo.domain.B;

/**
 * Spring-Engine 2015/9/20 17:31
 * fuquanemail@gmail.com
 */
public interface HelloService {
    public String sayHello(String name);

    B getB();
}
