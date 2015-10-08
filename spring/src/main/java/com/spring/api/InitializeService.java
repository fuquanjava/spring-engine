package com.spring.api;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * fuquanemail@gmail.com
 * 2015/9/30 15:38
 */
@Service("initializeService")
public class InitializeService implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 所有的bean都实例化完毕之后会调用
        System.out.println("InitializeService onApplicationEvent,事件源:"+contextRefreshedEvent.getSource());
    }
}
