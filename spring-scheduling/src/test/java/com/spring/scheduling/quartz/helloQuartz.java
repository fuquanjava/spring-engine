package com.spring.scheduling.quartz;

import com.spring.scheduling.quartz.job.SalesReportJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.nextGivenSecondDate;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * spring-engine 2016/2/20 15:35
 * fuquanemail@gmail.com
 */
public class HelloQuartz {
    public static void main(String[] args) {
        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            Date startTime = nextGivenSecondDate(null, 3);

            // define the job and tie it to our HelloJob class
            JobDetail job1 = JobBuilder.newJob(SalesReportJob.class)
                    .withIdentity("job1", "group1")
                    .usingJobData("name", "zs")
                    .usingJobData("counter", "1")
                    .build();

            JobDetail job2 = JobBuilder.newJob(SalesReportJob.class)
                    .withIdentity("job2", "group1")
                    .usingJobData("name", "ls")
                    .usingJobData("counter", "1")
                    .build();


            // Trigger the job to run now, and then repeat every 3 seconds
            SimpleTrigger trigger1 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startAt(startTime)
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(1)
                            .withRepeatCount(3))
                    .build();

            SimpleTrigger trigger2 = TriggerBuilder.newTrigger()
                    .withIdentity("trigger2", "group1")
                    .startAt(startTime)
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(1)
                            .withRepeatCount(10))
                    .build();



            // Tell quartz to schedule the job using our trigger
            // 一个trigger只能触发一个job,原因如下：

//            if (trigger.getJobKey() == null) {
//                trig.setJobKey(jobDetail.getKey());
//            } else if (!trigger.getJobKey().equals(jobDetail.getKey())) {
//                throw new SchedulerException(
//                        "Trigger does not reference given job!");
//            }


            Date scheduleTime1 = scheduler.scheduleJob(job1, trigger1);
            //scheduler.scheduleJob(job2, trigger2);

            System.err.println(job1.getKey() + " will run at: " + scheduleTime1 + " and repeat: " + trigger1.getRepeatCount()
                    + " times, every " + trigger1.getRepeatInterval() / 1000 + " seconds");

            // 在调用shutdown()之前，你需要给job的触发和执行预留一些时间
            Thread.sleep(60000);
            scheduler.shutdown();


        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
