package com.dubbo.service;

import com.alibaba.fastjson.JSON;
import com.dubbo.service.exception.BusinessException;
import com.dubbo.service.exception.BusinessExceptionA;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * fuquanemail@gmail.com 2016/6/15 9:24
 * description:
 * 1.0.0
 */
public class HelloServiceTest {

    private ClassPathXmlApplicationContext context = null;
    private HelloService helloService = null;

    @Before
    public void setUp() throws Exception {
        context =
                new ClassPathXmlApplicationContext("consumer/dubbo-consumer-unit.xml");
        context.start();

        helloService = (HelloService) context.getBean("helloService");
    }

    @After
    public void tearDown() throws Exception {
        context.destroy();
    }

    @Test
    public void testSayHello() throws Exception {
        while (true){
            System.err.println("loopi start-->");
            int i = helloService.loopI(5);
            System.err.println("loopi done: "+i);

            Thread.sleep(6000);
        }
    }

    @Test
    public void testTestRuntimeException() throws Exception {
        try {
            helloService.testRuntimeException(true, "A");
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof BusinessExceptionA) {
                BusinessExceptionA exception = (BusinessExceptionA) e;
                System.err.println(JSON.toJSONString(exception, true));
            }
            System.err.println("--------- BusinessExceptionA ------------");
        }


        try {
            helloService.testRuntimeException(true, null);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof BusinessException) {
                BusinessException exception = (BusinessException) e;
                System.err.println(JSON.toJSONString(exception, true));
            }
            System.err.println("----------BusinessException------------");
        }


    }

    @Test
    public void testTestCheckedException()  {
        try {
            helloService.testCheckedException(true);
        } catch (IOException e) {
            e.printStackTrace();
            if(e instanceof  IOException){
                System.err.println("----------IOException------------");
                System.err.println(JSON.toJSONString(e, true));
            }
        }
    }
}