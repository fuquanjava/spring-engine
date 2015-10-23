package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author: fuquanemail@gmail.com 2015/10/16 16:52
 * description:
 * @version: 1.0.0
 */
@Aspect
public class SystemArchitecture {
    /**
     * A Join Point is defined in the action layer where the method needs
     * a permission check.
     */
    @Pointcut("@annotation(com.spring.aop.UserAnnotation)")
    public void saveLog() {}

}
