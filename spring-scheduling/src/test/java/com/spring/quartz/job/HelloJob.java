package com.spring.quartz.job;

/**
 * spring-engine 2016/2/24 21:44
 * fuquanemail@gmail.com
 */
public class HelloJob {
    public void printMessage() {
        System.err.println("start I am called by MethodInvokingJobDetailFactoryBean using SimpleTriggerFactoryBean");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("end I am called by MethodInvokingJobDetailFactoryBean using SimpleTriggerFactoryBean");
    }
}
