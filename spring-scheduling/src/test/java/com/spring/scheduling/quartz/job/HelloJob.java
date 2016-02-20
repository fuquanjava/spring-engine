package com.spring.scheduling.quartz.job;

import org.apache.commons.lang.time.DateFormatUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * spring-engine 2016/2/20 16:00
 * fuquanemail@gmail.com
 */
public class HelloJob implements Job{
    static final String DATA_FORMAT="yyyy-MM-dd hh:mm:ss";

    //JobData 中的key 自动注入到属性
    private String parameter;

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Date now = new Date();
        System.err.println(DateFormatUtils.format(now, DATA_FORMAT) +", hello job");

        String value = context.getJobDetail().getJobDataMap().getString("parameter");
        System.err.println(value);

        System.err.println(parameter);

    }
}
