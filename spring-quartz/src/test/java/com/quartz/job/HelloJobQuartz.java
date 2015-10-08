package com.quartz.job;

import org.quartz.*;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.DateBuilder.*;
/**
 * Spring-Engine 2015/10/2 8:29
 * fuquanemail@gmail.com
 */
public class HelloJobQuartz {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        Scheduler sched = schedFact.getScheduler();
        sched.start();
        // define the job and tie it to our HelloJob class
        //public JobBuilder withIdentity(String name, String group)
        // 在一个group1中，name必须是唯一的
        JobDetail job = newJob(HelloJob.class).withIdentity("myJob", "group1").build();
        // Trigger the job to run now, and then every 40 seconds
        Trigger trigger1 = newTrigger().withIdentity("myTrigger").startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever()).build();


        Trigger trigger2 = newTrigger()
                .withIdentity("trigger3", "group1").startNow()
                .withSchedule(cronSchedule("0/3 * * * * ?"))
                .build();

        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job, trigger1);
        sched.scheduleJob(job, trigger2);

        Thread.sleep(10000);
        sched.shutdown(); //有个重载的方法，job是否执行完毕
    }
}
