package com.dubbo.extension;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Filter;
import org.springframework.beans.factory.InitializingBean;

/**
 * fuquanemail@gmail.com
 */
public class MyDubboExtensionLoader implements InitializingBean {
    public static void loadExtension() {
        ExtensionLoader extensionLoader = ExtensionLoader.getExtensionLoader(Filter.class);
        System.err.println("DubboExtensionLoader---" + extensionLoader);

        Object o =  extensionLoader.getExtension("consumercontext");
        System.err.println("consumercontext class name:"+ o.getClass().getSimpleName());



    }

    @Override
    public void afterPropertiesSet() throws Exception {
        loadExtension();
    }
}
