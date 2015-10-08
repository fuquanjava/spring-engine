package com.spring.api;

import com.spring.api.bean.Bean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * fuquanemail@gmail.com
 * 2015/9/30 15:38
 */
@Service("initializeService")
public class InitializeService implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Map<String , Bean> map =
                SpringContextHolder.applicationContext.getBeansOfType(Bean.class);
        System.out.println(map);
    }
}
