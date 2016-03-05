package com.spring.quartz.job;

import org.apache.commons.lang.time.DateFormatUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import uitl.DateUtil;

import java.util.Date;

/**
 * fuquanemail@gmail.com 2016/2/26 10:49
 * description:
 * 1.0.0
 */
public class HelloJob3 extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.err.println(" 开始执行 ScheduledJob");

        Date nextFireTime = context.getNextFireTime();
        System.err.println("下次触发时间:" + DateFormatUtils.format(nextFireTime, DateUtil.LONG_DATE_FORMAT_STR));
        System.err.println("触发次数:" + context.getRefireCount());

        JobDataMap jobDataMap = context.getMergedJobDataMap();

        System.err.println(" 执行完毕 ScheduledJob");

    }
}
