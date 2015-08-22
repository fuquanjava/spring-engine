package com.spring.cache.common;

import java.util.UUID;

/**
 * Spring-demo
 *
 */
public class AccountService {
    private CacheManager<Account> cacheManager;

    public AccountService(CacheManager<Account> cacheManager) {
        this.cacheManager = cacheManager;
    }

    public Account getAccountByName(String acctName) {
        Account account = cacheManager.getValue(acctName);
        if(null ==account){
            account = this.getFromDB(acctName);
            cacheManager.addOrUpdateCache(acctName, account);
            return  account;
        }

        return account;
    }

    public void evictCache(){
        cacheManager.evictCache();
    }

    private Account getFromDB(String acctName) {
        System.out.println("real querying db..."+acctName);
        return new Account(UUID.randomUUID().toString().replaceAll("-",""), acctName);
    }
}
