package com.spring.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * spring-engine 2016/2/24 21:35
 * fuquanemail@gmail.com
 */
public class ScheduledJob extends QuartzJobBean {
    static final String DATA_FORMAT="yyyy-MM-dd hh:mm:ss";

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.err.println(" 开始执行 ScheduledJob");

       /* Date nextFireTime = context.getNextFireTime();
        System.err.println("下次触发时间:"+ DateFormatUtils.format(nextFireTime,DATA_FORMAT));
        System.err.println("触发次数:"+ context.getRefireCount());

        JobDataMap jobDataMap = context.getMergedJobDataMap();
        */

        JobInfo jobInfo = (JobInfo)context.getMergedJobDataMap().get("scheduleJob");

        System.err.println(jobInfo);

        System.err.println(" 执行完毕 ScheduledJob");

    }
}
