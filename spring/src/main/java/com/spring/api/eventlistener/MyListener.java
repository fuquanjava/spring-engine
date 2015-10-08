package com.spring.api.eventlistener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * fuquanemail@gmail.com
 * 2015/10/8 15:27
 */
//注入Listener,纳入spring管理
@Component("myListener")
public class MyListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("事件发生，业务操作, event:"+event+",事件源:"+event.getSource());
    }
}
