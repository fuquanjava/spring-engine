package org.springframework.aop.log;

import org.springframework.aop.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * fuquanemail@gamil.com
 * Date: 14-6-25 下午3:58
 * 切面
 */

// 只写 @Aspect 也可以，表明这是个切面.

@Component("optLogger")
@Aspect
public class AnnotationOptLogger {

    private static final Logger logger = LoggerFactory.getLogger(AnnotationOptLogger.class);


   // @Before(value = "execution(* spring.aop.service.*..*(..)) && args(str)" ,argNames = "str")
    public void beforeLogger(String str) {
        logger.info("Before 记录日志操作Before. 在目标方法调用乊前执行。不能阻止后续执行，除非抛异常..");
    }

    /** annotation：使用“@annotation(注解类型)”匹配当前执行方法持有指定注解的方法；
     * 注解类型也必须是全限定类型名；
     *
     * 当前执行方法上持有注解 spring.aop.Log 将被匹配
     */
    // @Before(value = "@annotation(spring.aop.Log)")
    public void annotation() {
        logger.info("Before 记录日志操作Before. 在目标方法调用乊前执行。不能阻止后续执行，除非抛异常..");
    }

    /** 匹配方法上有annotation,并且annotation的类型是 Log, 传递给通知的
     *  参数名 argNames="log".
     *  JoinPoint 可以省略，但必须放在参数第一个
     */
    @Before(value = "@annotation(log)" , argNames = "log")
    public void annotationAndArgs(JoinPoint jp, Log log) {
        System.err.println("Log," + log.value());
    }


    /** args属于动态切入点，这种切入点开销非常大，非特殊情况最好不要使用；
     *  任何一个以接受“传入参数类型为 java.io.Object” 开头，其后可跟任意个任意类型的参数的方法执行，
     *  args指定的参数类型是在运行时动态匹配的
     */
    // @After(value = "args(java.lang.Object)")
    public void afterLogger() {
        logger.info("After =========记录日志操作After..在目标方法调用乊后执行。目标方法正常戒异常都执行。.");
    }

    //异常通知:发生异常执行，如果在目标方法中进行try{} catch(){} 的话，异常通知也不会执行
    //@AfterThrowing(value = "servicePointcut()", throwing = "e")
    public void afterThrowingLogger(Exception e) {
        logger.info("afterThrowingLogger =========记录日志操作afterThrowingLogger..在目标方法调用发生异常乊后执行。。.");
        e.printStackTrace();; //可以捕获异常
        logger.info("异常通知捕获异常 [{}]", e.getMessage());
    }


    // 1.后置通知是在运行目标方法执行成功后调用的，如果目标方法执行失败（出现异常），后置通知将不被调用
    // 2.后置通知可以得到目标方法的返回值
    //@AfterReturning(value = "servicePointcut()", returning = "returnValue")
    public void afterReturningLogger(Object returnValue) {
        logger.info("After =========记录日志操作afterReturningLogger..在目标方法调用乊后执行。目标方法正常结束才执行。。.");
        logger.info("returnValue = [{}]", returnValue);
    }

    //@Around(value = "servicePointcut()") //在目标方法调用乊前和乊后执行
    public Object aroundLogger(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("Around 方法执行之前");
        Object obj = pjp.proceed();  // proceed()   Proceed with the next advice or target method invocation
        logger.info("Around 方法执行之后");
        String methodName = pjp.getSignature().getName();
        String className = pjp.getSignature().getClass().getSimpleName();
        logger.info("在" + className + "执行" + methodName + "操作");
        return obj;
    }


    // @Pointcut(value = "切入点表达式或命名切入点" ,argNames = "参数名称")
    // 利用空的方法来完成切入点表达式,方法必须是返回void类型）
    @Pointcut("execution(* rabbitmq.spring.aop.service.* ..*(..))")
    public void servicePointcut() {

    }
}
