package org.springframework.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * spring-engine 2015/10/29 22:02
 * fuquanemail@gmail.com
 */
@Scope("prototype")
@Component(value = "myTask")
public class MyTask implements Runnable {

    @Autowired
    private MyService myService;

    @Override
    public void run() {
        System.err.println("MyTask" + this);
        myService.sayHello();
    }
}
