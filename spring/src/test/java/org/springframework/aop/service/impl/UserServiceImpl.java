package org.springframework.aop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.aop.Log;
import org.springframework.aop.service.UserService;

/**
 * fuquanemail@gamil.com
 * Date: 14-6-25 下午2:51
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    private static  final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Log(value = "保存用户")
    @Override
    public boolean  save() {
        logger.info("保存用户信息");

        return true; //返回给后置通知
    }
    @Log(value = "保存用户")
    @Override
    public void delete() {
        logger.info("删除用户信息");
    }

    @Override
    public void update() {
        logger.info("更新用户信息");
    }

    @Override
    public void sayBefore(String param ) {
        System.err.println("UserServiceImpl param, "+ param);
    }

    @Override
    public void sayAfterReturning() {
        System.err.println("sayAfterReturning");
    }

    @Override
    public void after(String str) {
        System.err.println("UserServiceImpl , "+ str);
    }
}
