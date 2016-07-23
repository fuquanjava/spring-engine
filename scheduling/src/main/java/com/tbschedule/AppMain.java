package com.tbschedule;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * tbschedule 2015/10/3 15:20
 * fuquanemail@gmail.com
 */
public class AppMain {
    // 策略名称:orderStrategy
    // 任务名称: singleOrderTask

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

//        TBScheduleManagerFactory scheduleManagerFactory = new TBScheduleManagerFactory();
//        Properties p = new Properties();
//        p.put("zkConnectString", "localhost:2181");
//        p.put("rootPath", "/schedule/order");
//        p.put("zkSessionTimeout", "60000");
//        p.put("isCheckParentPath", "true");
//        p.put("userName", "");
//        p.put("password", "");
//        scheduleManagerFactory.setApplicationContext(ctx);
//        try {
//            scheduleManagerFactory.init(p);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
