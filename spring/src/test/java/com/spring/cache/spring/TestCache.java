package com.spring.cache.spring;

import com.spring.cache.BaseTest;
import com.spring.cache.common.Account;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Spring-demo
 * 2015/8/10 16:12
 */
public class TestCache extends BaseTest {


    @Autowired
    AccountService accountService;

    @Test
    public void t1(){
        accountService.getAccountByName("somebody");
        // 第二次查询，应该不查数据库，直接返回缓存的值
        accountService.getAccountByName("somebody");
        accountService.getAccountByName("somebody");
        accountService.getAccountByName("somebody");
        logger.info("清空缓存");

        accountService.reload();
        accountService.getAccountByName("somebody");
        accountService.getAccountByName("somebody");

        logger.info("更新缓存");
        Account account = new Account("1","bodysome");
        accountService.updateAccount(account);
        accountService.getAccountByName("bodysome");
        logger.info("更新缓存后查询第二个");
        accountService.getAccountByName("bodysome");

    }

    @Test
    public void t2(){
        logger.info("长度>4,每次都查询DB");
        accountService.getActByCondition("abcdef");
        accountService.getActByCondition("abcdef");
        accountService.getActByCondition("abcdef");
        accountService.getActByCondition("abcdef");

        logger.info("长度<4, 缓存缓存");
        accountService.getActByCondition("abc");
        accountService.getActByCondition("abc");
        accountService.getActByCondition("abc");
        accountService.getActByCondition("abc");
        accountService.getActByCondition("abc");


    }
}
