package com.spring.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HelloService {

    @AspectAnnotation("123")
    public String sayHello(String name) {
        log.info("sayHello.");
        return "hello:" + name;
    }

}
