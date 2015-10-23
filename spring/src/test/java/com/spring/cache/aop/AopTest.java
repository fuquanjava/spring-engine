package com.spring.cache.aop;

import com.service.UserService;
import com.spring.cache.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: fuquanemail@gmail.com 2015/10/16 15:40
 * description:
 * @version: 1.0.0
 */
public class AopTest extends BaseTest{

    @Autowired
    UserService userService;
    @Test
    public void add(){
        userService.add("zhello");

        userService.update(1L);
    }

}
