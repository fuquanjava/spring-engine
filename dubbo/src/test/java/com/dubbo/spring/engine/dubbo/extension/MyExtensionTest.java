package com.dubbo.spring.engine.dubbo.extension;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.dubbo.extension.MyExtension;

/**
 * fuquanemail@gmail.com
 */
public class MyExtensionTest {
    public static void main(String[] args) {
        //设置dubbo使用slf4j来记录日志
        System.setProperty("dubbo.application.logger","slf4j");

        ExtensionLoader extensionLoader
                = ExtensionLoader.getExtensionLoader(MyExtension.class);

        MyExtension myExtension = (MyExtension) extensionLoader.getAdaptiveExtension();

        System.out.println(myExtension.sayHello("bieber", MyExtension.DEFAULT_TYPE));

    }
}
