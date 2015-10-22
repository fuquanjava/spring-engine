package com.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * spring-engine 2015/10/22 22:09
 * fuquanemail@gmail.com
 */
@Component("optLogger")
@Aspect
public class Logger {
    private static  final org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    @Around(value = "servicePointcut() && args(name , ..)") //在目标方法调用乊前和乊后执行
    public Object aroundLogger(ProceedingJoinPoint pjp , String name) throws Throwable {
        logger.info("Around 方法执行之前");
        Object obj = pjp.proceed();  // proceed()   Proceed with the next advice or target method invocation
        logger.info("Around 方法执行之后");
        String methodName = pjp.getSignature().getName();
        String className = pjp.getSignature().getClass().getSimpleName();
        logger.info("在" + className + "执行" + methodName + "操作");
        return obj;
    }
    // @Pointcut(value = "切入点表达式或命名切入点" ,argNames = "参数名称")
    /**
     * 利用空的方法来完成切入点表达式
     */
    @Pointcut("execution(* com.service.* ..*(..))")
    public void servicePointcut(){

    }

    //注解
    @Pointcut("@annotation(OperatLog)")
    public void annotationPointcut(){}

    //参数
    @Pointcut("@args(obj , ..)")
    public void argsPointcut(){}
}
