package com.spring.core.aop;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
@Documented
public @interface AspectAnnotation {

    String value() default "";
}
