package com.spring;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Spring-demo
 * 2015/8/10 16:05
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    protected AbstractApplicationContext context = null;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
    }

    @After
    public void tearDown() throws Exception {
        context.destroy();
    }
}
