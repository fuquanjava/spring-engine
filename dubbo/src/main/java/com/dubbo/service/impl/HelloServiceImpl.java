package com.dubbo.service.impl;

import com.dubbo.domain.B;
import com.dubbo.service.HelloService;
import com.dubbo.service.exception.BusinessException;

import java.util.Date;

/**
 * Spring-Engine 2015/9/20 17:31
 * fuquanemail@gmail.com
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello : " + name;
    }

    @Override
    public B getB() {
        B b = new B();
        b.setA("a");
        b.setDate(new Date());
        System.err.println("HelloServiceImpl  " + b);
        return b;
    }

    @Override
    public String testExp(boolean throwException) {
        if (throwException) {
            throw new BusinessException("10001", "测试异常");
        }

        return "invoke success !";
    }
}
