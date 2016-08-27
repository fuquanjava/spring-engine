package com.dubbo.extension;

import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.fastjson.JSON;

/**
 * fuquanemail@gmail.com
 */
@Adaptive
public class MyAdaptiveExtension implements MyExtension {

    @Override
    public String sayHello(String name, String type) {
        ExtensionLoader extensionLoader =
                ExtensionLoader.getExtensionLoader(MyExtension.class);

        System.out.println("extensionLoader:" + JSON.toJSONString(extensionLoader));

        MyExtension myExtension;

        switch (type) {
            case DEFAULT_TYPE:
                myExtension = (MyExtension) extensionLoader.getExtension("default");
                break;
            case OTHER_TYPE:
                myExtension = (MyExtension) extensionLoader.getExtension("other");
                break;
            default:
                myExtension = (MyExtension) extensionLoader.getDefaultExtension();
        }
        return myExtension.sayHello(name, type);

    }
}
