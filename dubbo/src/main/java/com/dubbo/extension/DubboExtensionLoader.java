package com.dubbo.extension;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.ProxyFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;


/**
 * fuquanemail@gmail.com
 */
public class DubboExtensionLoader implements ApplicationListener {

    private volatile boolean initFlag = false;

    public static void loadExtension() {
        ExtensionLoader extensionLoader = ExtensionLoader.getExtensionLoader(Filter.class);
        System.err.println("DubboExtensionLoader---" + extensionLoader);

        Object o = extensionLoader.getExtension("consumercontext");
        System.err.println("consumercontext class name:" + o.getClass().getSimpleName());

        ExtensionLoader providerExtensionLoader = ExtensionLoader.getExtensionLoader(ProxyFactory.class);
        o = providerExtensionLoader.getExtension("javassist");
        System.err.println("javassist class name:" + o.getClass().getSimpleName());

    }


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (!initFlag) {
            loadExtension();
            initFlag = true;
        }
    }
}
