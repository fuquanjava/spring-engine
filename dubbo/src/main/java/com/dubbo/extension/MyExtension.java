package com.dubbo.extension;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * fuquanemail@gmail.com
 */
@SPI("default")
public interface MyExtension {

    String sayHello(String name, String type);

    String DEFAULT_TYPE = "DEFAULT_TYPE";
    String OTHER_TYPE = "OTHER_TYPE";
}
