package com.spring.quartz;

import com.spring.quartz.job.JobInfo;
import org.quartz.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import com.spring.quartz.core.JobManagement;
import com.spring.quartz.job.ScheduledJob;

import java.util.List;

/**
 * spring-engine 2016/2/25 22:52
 * fuquanemail@gmail.com
 */
public class SpringQuartzMain {
    public static void main(String[] args) throws SchedulerException {
        testSpringQuartz();

    }

    public static void testSpringQuartz() throws SchedulerException {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        context.start();
        System.err.println(" quartz 启动成功");

        Scheduler scheduler = context.getBean(SchedulerFactoryBean.class).getScheduler();
        List<JobInfo> jobInfoList = JobManagement.getAllJobs();

        for (JobInfo job : jobInfoList) {
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            //不存在，创建一个
            if (null == trigger) {
                JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class)
                        .withIdentity(job.getJobName(), job.getJobGroup()).build();
                jobDetail.getJobDataMap().put("scheduleJob", job);
                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getCronExpression());
                //按新的cronExpression表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                // Trigger已存在，那么更新相应的定时设置
                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getCronExpression());
                //按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                        .withSchedule(scheduleBuilder).build();
                //按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        }

    }
}
