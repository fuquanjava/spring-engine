package com.dubbo.service;

import java.io.IOException;

/**
 * Spring-Engine 2015/9/20 17:31
 * fuquanemail@gmail.com
 */
public interface HelloService {

    String sayHello(String name);

    int loopI(int times);

    String testRuntimeException(boolean throwException, String throwsType);

    String testCheckedException(boolean throwException) throws IOException;

}
