package com.spring.quartz;

import com.spring.quartz.core.JobManagement;
import com.spring.quartz.core.QuartzJobFactory;
import com.spring.quartz.core.ScheduleJob;
import org.quartz.*;
import org.quartz.Trigger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * spring-engine 2016/2/25 22:52
 * fuquanemail@gmail.com
 */
public class SpringQuartzMain {
    public static void main(String[] args) throws SchedulerException {
        testSpringQuartz();


    }

    public static void testAllJobs(Scheduler scheduler) throws SchedulerException{
        List<JobExecutionContext> jobExecutionContextList =  scheduler.getCurrentlyExecutingJobs();
        if(CollectionUtils.isEmpty(jobExecutionContextList)){
            System.err.println("job list is empty");
        }else{

            for(JobExecutionContext jobExecutionContext : jobExecutionContextList ){
                JobDetail jobDetail = jobExecutionContext.getJobDetail();
                System.err.println("job key: "+jobDetail.getKey());
                Trigger trigger = jobExecutionContext.getTrigger();
                System.err.println("triger key :"+ trigger.getKey());
            }
        }

    }

    public static void testSpringQuartz() throws SchedulerException {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        context.start();
        System.err.println(" quartz 启动成功");

        Scheduler scheduler = context.getBean(SchedulerFactoryBean.class).getScheduler();
        List<ScheduleJob> jobInfoList = JobManagement.getAllJobs();

        for (ScheduleJob job : jobInfoList) {
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            //不存在，创建一个
            if (null == trigger) {
                System.err.println("trigger不存在，创建trigger");
                JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                        .withIdentity(job.getJobName(), job.getJobGroup()).build();
                jobDetail.getJobDataMap().put("scheduleJob", job);
                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getCronExpression());
                //按新的cronExpression表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                System.err.println("trigger已存在，使用原来的trigger");
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
        scheduler.start();
        testAllJobs(scheduler);
    }
}
