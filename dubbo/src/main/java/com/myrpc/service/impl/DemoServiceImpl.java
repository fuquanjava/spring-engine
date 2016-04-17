package com.myrpc.service.impl;

import com.myrpc.service.DemoService;

/**
 * Created by fuquan-pc on 2016/4/17.
 *
 * service implements
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public void sayHello() {
        System.out.println("hello demo service ");
    }
}
