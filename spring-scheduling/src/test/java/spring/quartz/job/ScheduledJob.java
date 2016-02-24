package spring.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * spring-engine 2016/2/24 21:35
 * fuquanemail@gmail.com
 */
public class ScheduledJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.err.println(" hello job");
    }
}
