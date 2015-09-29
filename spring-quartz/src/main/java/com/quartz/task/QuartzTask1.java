package com.quartz.task;


import java.text.SimpleDateFormat;
import java.util.Date;

public class QuartzTask1 {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public void run(){
        System.out.println(Thread.currentThread().getName()+" -- Quartz start run ..."+getNow());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" -- Quartz run over ..."+getNow());
    }
    public String getNow(){
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
}
