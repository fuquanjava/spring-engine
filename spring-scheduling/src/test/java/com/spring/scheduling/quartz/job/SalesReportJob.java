package com.spring.scheduling.quartz.job;

import org.apache.commons.lang.time.DateFormatUtils;
import org.quartz.*;

import java.util.Date;

/**
 * spring-engine 2016/2/20 22:12
 * fuquanemail@gmail.com
 */
@DisallowConcurrentExecution
public class SalesReportJob implements Job {

    private String name;

    private int counter;

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        counter++;
        Date now = new Date();
        System.err.println(jobKey + " ," + DateFormatUtils.format(now, HelloJob.DATA_FORMAT) + name + " 执行 ,结果" + counter);

        /*if (2 > 1) {
            try {
                int i = 1 / 0;
            } catch (Exception e) {
                JobExecutionException e2 =
                        new JobExecutionException(e);
                // Quartz will automatically unschedule
                // all triggers associated with this job
                // so that it does not run again
                //e2.setUnscheduleAllTriggers(true);

                // 触发,会造成死循环
                e2.setRefireImmediately(true);
                throw e2;
            }

        }*/

        try {
            Thread.sleep(5000);
        } catch (Exception ignore) {
            //
        }
        now = new Date();
        System.err.println(jobKey + " ," + DateFormatUtils.format(now, HelloJob.DATA_FORMAT) + name + " 执行完毕");

    }
}
