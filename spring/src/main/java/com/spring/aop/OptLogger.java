package com.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author: fuquanemail@gmail.com 2015/10/16 15:14
 * description:
 * @version: 1.0.0
 */
@Component("optLogger")
@Aspect
public class OptLogger {


    //@Around(value = "servicePointcut()") //在目标方法调用乊前和乊后执行
    public Object aroundLogger(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("================start================");
        Object obj = null;
        try {
            obj = pjp.proceed();  // proceed()   Proceed with the next advice or target method invocation

        } catch (Exception e) {

        }
        String methodName = pjp.getSignature().getName();
        String className = pjp.getSignature().getClass().getSimpleName();
        //logger.info("在" + className + "执行" + methodName + "操作");
        System.out.println("================end================");
        return obj;
    }


    // @Before(value = "servicePointcut1()")
    public void before6(JoinPoint jp) {
        System.out.println("================start================");
        Object obj = null;
        try {
            Signature signature = jp.getSignature();
            Object a = jp.getTarget();

            MethodSignature ms = (MethodSignature) jp.getSignature();
            Method method = ms.getMethod();
            System.out.println(method.getAnnotation(UserAnnotation.class).value());

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("================end================");
    }

    @Pointcut("@annotation(com.spring.aop.UserAnnotation)")
    public void log() {
        System.out.println("我是一个切入点");
    }

    /**
     * 在所有标注@Log的地方切入
     *
     * @param joinPoint
     */
    // @Before("log()")
    public void beforeExec(JoinPoint joinPoint) throws NoSuchMethodException {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        //System.out.println(method);
        //System.out.println(method.getAnnotation(UserAnnotation.class).value()+"标记");
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Class[] argsClazz = new Class[args.length];

        Class clazz = joinPoint.getTarget().getClass();


        Method me = clazz.getDeclaredMethod(methodName, String.class);

        UserAnnotation tracedAnnotation = AnnotationUtils.findAnnotation(me,
                UserAnnotation.class);
        String traceName = tracedAnnotation.value();


        System.out.println(traceName);

    }


    // @Pointcut(value = "切入点表达式或命名切入点" ,argNames = "参数名称")

    /**
     * 利用空的方法来完成切入点表达式
     */
    @Pointcut(value = "execution(@com.spring.aop.UserAnnotation * *(..))")
    public void servicePointcut1() {

    }

    @Pointcut(value = "execution(* com.service..*.*(..))")
    public void servicePointcut2() {

    }

    //环绕通知方法
    @Around("log()")
    public Object doWriteLog(ProceedingJoinPoint pjp) throws Throwable {
        // 拦截的实体类
        Object target = pjp.getTarget();
        // 拦截的方法名称
        String methodName = pjp.getSignature().getName();
        // 拦截的方法参数
        Object[] args = pjp.getArgs();

        // 拦截的放参数类型
        Class[] parameterTypes = ((MethodSignature) pjp.getSignature())
                .getMethod().getParameterTypes();

        Object object = null;
        // 获得被拦截的方法
        Method method = target.getClass().getMethod(methodName, parameterTypes);
        if (null != method) {
            // 判断是否包含自定义的注解
            if (method.isAnnotationPresent(UserAnnotation.class)) {
                // 获取自定义注解实体
                UserAnnotation myAnnotation = method
                        .getAnnotation(UserAnnotation.class);
                //日志类实体类
                System.out.println(myAnnotation.value());
                try {
                    object = pjp.proceed();// 执行该方法
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                return object;
            }
            return object;
        }
        return object;
    }

}