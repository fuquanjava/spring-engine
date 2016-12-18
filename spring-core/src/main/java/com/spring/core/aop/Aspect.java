package com.spring.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * fuquanemail@gmail.com
 */
@Slf4j
@org.aspectj.lang.annotation.Aspect
@Component
public class Aspect {

    @Pointcut("@annotation(com.spring.core.aop.AspectAnnotation)")
    public void annotation() {
    }

    @Before("annotation() && @annotation(aspectAnnotation)")
    public void beforeAdvice(JoinPoint joinPoint, AspectAnnotation aspectAnnotation) {
        log.info("beforeAdvice aspectAnnotation={}", aspectAnnotation);

        if("123".equals(aspectAnnotation.value())){
            throw new RuntimeException("异常拦截");
        }
    }
}
