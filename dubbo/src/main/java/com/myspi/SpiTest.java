package com.myspi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by fuquan-pc on 2016/4/24.
 */
public class SpiTest {
    public static void main(String[] args) {
        /**
         * public final class ServiceLoader<S> implements Iterable<S> {
                    private static final String PREFIX = "META-INF/services/";
         */

        ServiceLoader<Spi> serviceLoader = ServiceLoader.load(Spi.class);
        Iterator<Spi> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            Spi spi = iterator.next();
            String hello = spi.sayHello();
            System.out.println(hello);
        }


    }
}
