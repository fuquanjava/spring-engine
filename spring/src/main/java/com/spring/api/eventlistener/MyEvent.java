package com.spring.api.eventlistener;

import org.springframework.context.ApplicationEvent;

/**
 * fuquanemail@gmail.com
 * 2015/10/8 15:22
 */
public class MyEvent extends ApplicationEvent {

    private String eventName;

    public MyEvent(Object source) {
        super(source);
        this.eventName = "aa";
    }
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public String toString() {
        return "MyEvent{" +
                "eventName='" + eventName + '\'' +
                '}';
    }
}
