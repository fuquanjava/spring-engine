package com.dubbo.extension;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * fuquanemail@gmail.com
 */
@SPI("default")
public interface MyExtension {
    String sayHello(String name, String type);
    String DEFAULT_TYPE = "DEFAULT_TYPE"; //仅限测试
    String OTHER_TYPE = "OTHER_TYPE";//仅限测试
}
