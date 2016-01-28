package javase.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * fuquanemail@gamil.com
 * Date: 14-7-1 下午5:17
 */
public class JDKProxyFactory implements InvocationHandler {

    private Object target;


    /**单例*/
    private JDKProxyFactory(){}

    public static class JDKProxy{
        private static  final  JDKProxyFactory instance = new JDKProxyFactory();
        public static  JDKProxyFactory  getInstance(){
            return instance;
        }

    }
    /**
     * 绑定委托对象并返回一个代理类
     * @param target
     * @return
     */
    public Object bind(Object target) {
        this.target = target;
        //取得代理对象
        //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
        //取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    /**
     * method.invoke(target, args); 这里的 target一定要是 没有被代理的对象(原始的对象),
     * 如果 写成 method.invoke(proxy, args); 就会出现死循环
     *
     * @param proxy 代理的的对象
     * @param method 要调用的方法
     * @param args 方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("调用目标对象开始...");
        Object object = null;
        object = method.invoke(target, args);
        System.err.println("调用目标对象结束...");
        return object;
    }
}
