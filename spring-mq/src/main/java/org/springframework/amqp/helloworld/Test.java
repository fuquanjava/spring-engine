package org.springframework.amqp.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

/**
 * fuquanemail@gmail.com 2016/1/8 9:03
 * description:
 * 1.0.0
 */
public class Test {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(TestScheduled.class);


        System.err.println(getDiscountValue("50:50")); //人民币, 积分
//        2、1个积分相关于1元人民币
        System.err.println(getExchangeValue("1:2")); //金币1,人民币2


    }

    public static Double getDiscountValue(String value){
        if(StringUtils.isEmpty(value)){
            return null;
        }
        String [] arys = value.split(":");
        if(arys != null && arys.length == 2){
            double rmb = Double.parseDouble(arys[0]);
            double points = Double.parseDouble(arys[1]);
            return rmb / points;
        }
        return null;
    }


    public static Double getExchangeValue(String value){
        if(StringUtils.isEmpty(value)){
            return null;
        }
        String [] arys = value.split(":");
        if(arys != null && arys.length == 2){
            double points = Double.parseDouble(arys[0]);
            double rmb = Double.parseDouble(arys[1]);
            return rmb / points;
        }
        return null;
    }

}
