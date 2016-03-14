package system.core.busi.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import system.core.quartz.BaseJob;

/**
 * fuquanemail@gmail.com 2016/3/14 10:59
 * description:
 * 1.0.0
 */
public class TestJob1 extends BaseJob {
    @Override
    public Object executeJob(JobExecutionContext context) throws JobExecutionException {
        System.err.println("execute job -1 --------------------------");
        return "success";
    }
}
