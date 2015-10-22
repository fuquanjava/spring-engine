package com.service.impl;

import com.service.UserService;
import org.springframework.stereotype.Service;

/**
 * spring-engine 2015/10/22 21:10
 * fuquanemail@gmail.com
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public void sayHello() {
        System.err.println("hello ");
    }

    @Override
    public void sayName(String name) {
        System.err.println(name);
    }
}
