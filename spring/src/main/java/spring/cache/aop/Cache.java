package spring.cache.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * fuquanemail@gmail.com 2016/9/27 21:15
 * description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Cache {

    /**
     * 缓存key
     *
     * @return String
     */
    String key() default "";

    /**
     * 缓存时效,默认无限期
     *
     * @return long
     */
    long expire() default 0;

    /**
     * 缓存对象类型
     * @return
     */
    Class classType() default Object.class;
}
