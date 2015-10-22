package com.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * spring-engine 2015/10/22 21:15
 * fuquanemail@gmail.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperatLog {
    /**
     * 模块
     * @return
     */
    String module();

    /**
     * 动作
     * @return
     */
    String action();
}
