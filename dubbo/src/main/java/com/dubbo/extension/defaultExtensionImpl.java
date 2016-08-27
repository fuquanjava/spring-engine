package com.dubbo.extension;

/**
 * fuquanemail@gmail.com
 */
public class DefaultExtensionImpl implements MyExtension {
    @Override
    public String sayHello(String name, String type) {

        return "default say hello"+ name;
    }
}
