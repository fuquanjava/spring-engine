package system.core.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * fuquanemail@gmail.com 2016/3/14 10:53
 * description:
 * 1.0.0
 */
@DisallowConcurrentExecution
public abstract class BaseJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Object result = this.executeJob(context);
        System.err.println("Job 执行结果:" + result);

    }
    /**
     * job具体的业务逻辑
     *
     * @param context
     * @throws JobExecutionException
     */
    public abstract Object executeJob(JobExecutionContext context) throws JobExecutionException;

}
