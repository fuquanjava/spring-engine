package org.springframework.aop;

import spring.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.aop.service.UserService;

/**
 * spring-engine 2015/10/23 21:59
 * fuquanemail@gmail.com
 */
public class LogTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void beforeAdvice(){
        userService.sayBefore("hello spring");
    }
    @Test
    public void after(){
        userService.save();
        userService.after("hello spring");
    }

    @Test
    public void within(){
        userService.save();
    }

}
