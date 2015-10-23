package com.spring.aop;

import java.lang.annotation.*;

/**
 * @author: fuquanemail@gmail.com 2015/10/16 15:12
 * description:
 * @version: 1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserAnnotation {
    String value() default "";
}
