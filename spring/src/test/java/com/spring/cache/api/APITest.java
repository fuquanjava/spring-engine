package com.spring.cache.api;

import com.spring.cache.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * fuquanemail@gmail.com
 * 2015/9/30 15:27
 */
public class APITest extends BaseTest {

    @Test
    public void printGetBeansOfType(){
        Map map = new HashMap();
        map.put(null,"aa");
        map.put(null,"bb");


        System.out.println(map);



    }
}
