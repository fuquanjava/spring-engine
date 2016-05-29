package javase.proxy;

import javase.proxy.support.*;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * fuquanemail@gmail.com 2016/1/27 17:31
 * description:
 * 1.0.0
 */
public class TestProxy {

    /**
     * static Object	newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
     * 返回一个指定接口的代理类实例，该接口可以将方法调用指派到指定的调用处理程序。
     */
    @Test
    public void testJDKProxy2() {
        final UserService userService = new UserServiceImpl();
        ClassLoader classLoader = userService.getClass().getClassLoader();
        Class[] interfaces = userService.getClass().getInterfaces();

//        a.JDK会通过根据传入的参数信息动态地在内存中创建和.class 文件等同的字节码
//        b.然后根据相应的字节码转换成对应的class，
//        c.然后调用newInstance()创建实例

        UserService proxyUserService =
                (UserService) Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.err.println("proxy class name:" + proxy.getClass().getSimpleName());
                        System.err.println("method name:" + method.getName());
                        System.err.println(" args :" + Arrays.toString(args));

                        // 调用真正的实现
                        return method.invoke(userService, args);
                    }
                });

        proxyUserService.saveUser();

    }


    @Test
    public void testCglibProxy2(){
        CGLibProgrammer cgLibProgrammer = new CGLibProgrammer();
        //cglib 中加强器，用来创建动态代理
        Enhancer enhancer = new Enhancer();
        //设置要创建动态代理的类
        enhancer.setSuperclass(cgLibProgrammer.getClass());

        // 设置回调，这里相当于是对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实行intercept()方法进行拦截
        enhancer.setCallback(new MethodInterceptor(){
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                return methodProxy.invokeSuper(o, objects);
            }
        });
        CGLibProgrammer proxy =(CGLibProgrammer)enhancer.create();
        proxy.sayHello();
        proxy.sayFinalMethod();
    }

    @Test
    public void testJDKProxy() {
        JDKProxyFactory jdkProxyFactory = JDKProxyFactory.JDKProxy.getInstance();
        UserService service = (UserService) jdkProxyFactory.bind(new UserServiceImpl());
        service.saveUser();

    }

    @Test
    public void testCglibProxy() {
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory();
        UserService service = (UserService) cglibProxyFactory.getInstance(new UserServiceImpl());
        service.saveUser();
    }

    @Test
    public void testCglibProxyBean() {
        User user = new User();
        user.setId(1);
        user.setName("user");
        RefreshableProxy<User> refreshableProxy = new RefreshableProxy<User>(user);
        User proxy = refreshableProxy.getInstance();
        System.err.println(proxy);

        User user2 = new User();
        user2.setName("zhangsan");
        user2.setId(2);
        refreshableProxy.refresh(user2);

        System.err.println(proxy);


    }
}
