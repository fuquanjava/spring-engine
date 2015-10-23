package spring.aop.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.aop.service.UserService;

/**
 * fuquanemail@gamil.com
 * Date: 14-6-25 下午2:51
 */

public class UserServiceImpl implements UserService {
    private static  final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public boolean  save() {
        logger.info("保存用户信息");

        return true; //返回给后置通知
    }

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
}
