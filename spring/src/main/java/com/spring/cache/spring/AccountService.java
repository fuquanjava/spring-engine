package com.spring.cache.spring;

import com.spring.cache.common.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.UUID;

/**
 * Spring-demo
 */
public class AccountService {
    static Logger logger = LoggerFactory.getLogger(AccountService.class);

    // 使用了一个缓存名叫 accountCache , 在spring.xml 中配置.
    //    如果使用了 @Cacheable 注释，则当重复使用相同参数调用方法的时候，方法本身不会被调用执行，即方法本身被略过了，
    // 取而代之的是方法的结果直接从缓存中找到并返回了

    @Cacheable(value = "accountCache")
    public Account getAccountByName(String userName) {
        // 方法内部实现不考虑缓存逻辑，直接实现业务
        return getFromDB(userName);
    }


    // 当使用缓存的时候，不会执行方法中的逻辑
    @Cacheable(value="accountCache",condition="#name.length() <= 4")
    public Account getActByCondition(String name){
        logger.info("getActByCondition 方法逻辑 被执行 执行...");
        return getFromDB(name);
    }


    // 清空 accountCache 缓存
    @CacheEvict(value = "accountCache", key = "#account.getName()")
    public void updateAccount(Account account) {
        updateDB(account);
    }


    @CacheEvict(value="accountCache",allEntries=true)// 清空 accountCache 缓存
    public void reload() {
    }

    private Account getFromDB(String acctName) {
        logger.info("query the db with [{}]", acctName);
        return new Account(UUID.randomUUID().toString().replaceAll("-", ""), acctName);
    }
    private void updateDB(Account account) {
        logger.info("real update db..." , account.getName());
    }


}
