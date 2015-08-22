package com.spring.cache.common;

import org.junit.After;
import org.junit.Before;

/**
 * Spring-demo
 *
 */
public class Test {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @org.junit.Test
    public void test(){
        CacheManager cacheManager = new CacheManager();
        AccountService service = new AccountService(cacheManager);
        Account a1 = service.getAccountByName("a1");
        Account aa1 = service.getAccountByName("a1");

        System.out.println(a1);
        System.out.println(aa1);
    }
}
