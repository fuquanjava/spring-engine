package com.quartz;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;


public class SpringContextStart {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext
                context = new ClassPathXmlApplicationContext("spring-quartz.xml");
        context.start();
        System.in.read(); // 按任意键退出
    }
}
