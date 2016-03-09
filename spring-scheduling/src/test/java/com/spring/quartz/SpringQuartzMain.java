package com.spring.quartz;

import com.spring.quartz.core.JobManagement;
import com.spring.quartz.core.QuartzJobFactory;
import com.spring.quartz.core.ScheduleJob;
import org.quartz.*;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.utils.DBConnectionManager;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * spring-engine 2016/2/25 22:52
 * fuquanemail@gmail.com
 */
public class SpringQuartzMain {
    public static void main(String[] args) throws SchedulerException {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        context.start();
        System.err.println(" quartz 启动成功");

        SchedulerFactoryBean schedulerFactoryBean = context.getBean(SchedulerFactoryBean.class);
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.start();

        testSpringQuartz(scheduler);
        getAllSchedulers(scheduler);


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutdown();
    }

    public static void getAllJobs(Scheduler scheduler) throws SchedulerException {
        Set<JobKey> jobKeys =  scheduler.getJobKeys(GroupMatcher.anyJobGroup());
        if(org.apache.commons.collections.CollectionUtils.isEmpty(jobKeys)){

        }


    }
    public static void getAllSchedulers(Scheduler scheduler) throws SchedulerException {
        SchedulerContext schedulerContext = scheduler.getContext();
        System.err.println("schedulerContext："+schedulerContext);
        System.err.println("scheduler:" + scheduler);

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DBConnectionManager.getInstance().getConnection("quartz");

            String sql = "SELECT SCHED_NAME FROM QRTZ_JOB_DETAILS GROUP BY SCHED_NAME";

            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String schedulerName = rs.getString("SCHED_NAME");
                System.err.println("schedulerName:" + schedulerName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void testSpringQuartz(Scheduler scheduler) throws SchedulerException {

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

                //开始调度job
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
    }
}
