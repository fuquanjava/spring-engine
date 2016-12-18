package com.spring.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Copyright (C) 2016 Taobao.com 淘宝（中国）软件有限公司 版权所有.
 * 商家&运营平台-运营平台
 * <p>
 * Author： libai.fq
 * Date：   2016/12/18
 */
@Slf4j
public class AopTest {
    ClassPathXmlApplicationContext context = null;
    HelloService service = null;

    @Before
    public void init(){
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        service = context.getBean(HelloService.class);
    }

    @Test
    public void testBeforeAdvice(){
        log.info(service.sayHello("jack"));
    }
}
