package org.springframework.amqp.helloworld;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

/**
 * fuquanemail@gmail.com 2016/1/8 10:58
 * description:
 * 1.0.0
 */
@Configuration
public class TestScheduled {

    @Scheduled(fixedRate = 3000)
    public void sendMessage() {
        System.err.println("sendMessage ... ");
    }

    @Bean
    public BeanPostProcessor postProcessor() {
        return new ScheduledAnnotationBeanPostProcessor();
    }
}
