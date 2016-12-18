package spring.cache.aop;

import java.lang.annotation.*;

/**
 * fuquanemail@gmail.com 2016/9/27 21:22
 * description:
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Evict {
    /**
     * 缓存key
     *
     * @return String
     */
    String key() default "";

    /**
     * 缓存key数组
     *
     * @return String[]
     */
    String[] keys() default {};
}
