package com.dubbo.service.impl;

import com.dubbo.service.HelloService;
import com.dubbo.service.exception.BusinessException;
import com.dubbo.service.exception.BusinessExceptionA;

import java.io.IOException;

/**
 * Spring-Engine 2015/9/20 17:31
 * fuquanemail@gmail.com
 */

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "hello : " + name;
    }

    @Override
    public int loopI(int times) {
        int k = 0;
        for (int i = 0; i < times; i++) {

            System.err.println("start save:" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("save done:" + i);
            k++;
        }

        return k;
    }

    @Override
    public String testRuntimeException(boolean throwException, String throwsType) {
        if (throwException) {
            if ("A".equals(throwsType)) {
                throw new BusinessExceptionA("00a", "测试异常A");
            } else {
                throw new BusinessException("10001", "测试异常");
            }
        }

        return "invoke success !";
    }

    @Override
    public String testCheckedException(boolean throwException) throws IOException {
        if (throwException) {
            throw new IOException("IO异常");
        }

        return "invoke success !";
    }
}
