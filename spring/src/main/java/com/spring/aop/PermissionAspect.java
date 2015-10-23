package com.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author: fuquanemail@gmail.com 2015/10/16 16:53
 * description:
 * @version: 1.0.0
 */
@Aspect
public class PermissionAspect {

    @Before(value="com.spring.aop.SystemArchitecture.saveLog()&&"+
            "@annotation(userAnnotation)",argNames="userAnnotation")
    public void save(UserAnnotation userAnnotation){
        System.out.println("--------------"+userAnnotation.value());
    }
}
