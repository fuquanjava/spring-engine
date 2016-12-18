package springframework.aop;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * spring-engine 2015/10/24 22:42
 * fuquanemail@gmail.com
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Log {
    String value();
}
