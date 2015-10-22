package com.spring.cache.log;

import com.service.UserService;
import com.spring.cache.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * spring-engine 2015/10/22 21:30
 * fuquanemail@gmail.com
 */
public class LogTest extends BaseTest {
    @Autowired
    private UserService userService;

    @Test
    public void sayHello(){
        userService.sayHello();
        userService.sayName("man");
    }
}
