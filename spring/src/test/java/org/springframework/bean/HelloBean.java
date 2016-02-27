package org.springframework.bean;

/**
 * @author: fuquanemail@gmail.com 2015/10/23 10:13
 * description: bean的实例化过程
 * @version: 1.0.0
 */
public class HelloBean {

    private String name;

    public HelloBean(){
        this.name = "spring";
    }

    public HelloBean(String name){
        this.name = name;
    }

    public void sayName(){
        System.err.println("hello," + name);
    }
}
