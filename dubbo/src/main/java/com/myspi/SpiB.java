package com.myspi;

/**
 * Created by fuquan-pc on 2016/4/24.
 */
public class SpiB implements Spi {

    public SpiB() {
        System.err.println("Spi B init");
    }

    @Override
    public String sayHello() {
        return "hello B";
    }
}
