package javase.proxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * fuquanemail@gmail.com 2016/1/28 9:21
 * description:
 * 1.0.0
 */
public class CglibProxyFactory implements MethodInterceptor {
    private Object target;

    /**
     * 创建代理对象
     *
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    /**
     * * proxy.invokeSuper和proxy.invoke的区别。
     *   invokeSuper是退出当前interceptor的处理，进入下一个callback处理，
     *   invoke则会继续回调该方法，如果传递给invoke的obj参数出错容易造成递归调用
     * @param obj
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.err.println("调用目标对象开始...");
        Object object = null;
        object = methodProxy.invokeSuper(obj, args);
        System.err.println("调用目标对象结束...");
        return object;
    }
}
