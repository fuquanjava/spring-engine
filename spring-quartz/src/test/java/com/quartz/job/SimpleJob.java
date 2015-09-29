package com.quartz.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * fuquanemail@gmail.com
 * 2015/9/29 15:46
 */
public class SimpleJob {
    static Logger log = LoggerFactory.getLogger(SimpleJob.class);
    public void run() throws Exception {


        log.info("------- Initializing ----------------------");

        // 1、工厂模式 构建Scheduler的Factory，其中STD为Quartz默认的Factory，开发者亦可自行实现自己的Factory;Job、Trigger等组件
        SchedulerFactory sf = new StdSchedulerFactory();
        // 2、通过SchedulerFactory获得Scheduler对象
        Scheduler sched = sf.getScheduler();

        log.info("------- Initialization Complete -----------");

        // 3、org.quartz.DateBuilder.evenMinuteDate <下一分钟>  -- 通过DateBuilder构建Date
        Date runTime = DateBuilder.evenMinuteDate(new Date());

        log.info("------- Scheduling Job  -------------------");

        // 4、org.quartz.JobBuilder.newJob --通过JobBuilder构建Job
        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();

        // 5、通过TriggerBuilder进行构建
        //Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime ).build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/3 * * * * ?");
        Trigger trigger = scheduleBuilder.build();
        

        // 6、工厂模式，组装各个组件<JOB，Trigger>
        sched.scheduleJob(job, scheduleBuilder.build());

        // [group1.job1] will run at:
        log.info(job.getKey() + " will run at: " + runTime);

        // 7、start
        sched.start();

        log.info("------- Started Scheduler -----------------");

        log.info("------- Waiting 65 seconds... -------------");
        try {
            // wait 65 seconds to show job
            Thread.sleep(3L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }

        // shut down the scheduler
        log.info("------- Shutting Down ---------------------");
        // 8、通过Scheduler销毁内置的Trigger和Job
        sched.shutdown(true);
        log.info("------- Shutdown Complete -----------------");
    }

    public static void main(String[] args) throws Exception {

        SimpleJob example = new SimpleJob();
        example.run();

    }
}
