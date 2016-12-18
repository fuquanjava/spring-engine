package springframework.thread;

import org.springframework.stereotype.Service;

/**
 * spring-engine 2015/10/29 22:03
 * fuquanemail@gmail.com
 */
@Service("myService")
public class MyService {
    public void sayHello(){
        System.err.println("hello");

    }
}
