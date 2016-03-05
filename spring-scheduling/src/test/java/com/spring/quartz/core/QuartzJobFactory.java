package com.spring.quartz.core;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * spring-engine 2016/2/24 21:35
 * fuquanemail@gmail.com
 * 定时任务运行工厂类
 *
 */
public class QuartzJobFactory extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.err.println("任务成功运行");
        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
        System.err.println("任务名称 = [" + scheduleJob.getJobName() + "]");

    }
}
