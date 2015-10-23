package com.service.impl;

import com.service.UserService;
import com.spring.aop.UserAnnotation;
import org.springframework.stereotype.Service;

/**
 * @author: fuquanemail@gmail.com 2015/10/16 15:11
 * description:
 * @version: 1.0.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @UserAnnotation("保存用户")
    @Override
    public void add(String name) {
        System.out.println("保存成功");
    }

    @Override
    public void update(Long id) {
        System.out.println("修改成功");
    }
}
