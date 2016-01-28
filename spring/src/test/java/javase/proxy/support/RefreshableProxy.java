package javase.proxy.support;

import com.google.common.base.Preconditions;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * fuquanemail@gmail.com 2016/1/28 9:47
 * description:
 * 1.0.0
 */
public class RefreshableProxy<T> implements MethodInterceptor {

    private T target;

    private final T targetProxy;

    public RefreshableProxy(T target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        targetProxy = (T) enhancer.create();
    }

    public void refresh(final T target) {
        this.target = Preconditions.checkNotNull(target);
    }

    public T getInstance() {
        return targetProxy;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        return methodProxy.invoke(target,args);
    }
}
