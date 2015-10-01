package com.quartz.task;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * fuquanemail@gmail.com
 * 2015/9/29 15:10
 */
@Component("printMsgTask")
public class PrintMsgTask implements Serializable{
    public void printMsg(){
        System.out.println("printMsg.... ");
    }
}
