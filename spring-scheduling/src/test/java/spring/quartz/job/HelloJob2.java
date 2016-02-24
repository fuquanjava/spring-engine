package spring.quartz.job;

import org.springframework.stereotype.Component;

/**
 * spring-engine 2016/2/24 21:44
 * fuquanemail@gmail.com
 */


@Component("helloJob2")
public class HelloJob2 {
    public void printMessage() {
        System.err.println("helloJob2 called by MethodInvokingJobDetailFactoryBean using SimpleTriggerFactoryBean");
    }
}
