package com.dubbo.extension;

/**
 * fuquanemail@gmail.com
 */
public class OtherExtensionImpl implements MyExtension {
    @Override
    public String sayHello(String name, String type) {

        return "other say hello"+ name;
    }
}
