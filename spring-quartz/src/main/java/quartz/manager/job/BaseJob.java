package quartz.manager.job;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import quartz.manager.source.DynamicQuartz;

/**
 * 自动任务调度指定的任务需要实现此接口
 * 
 * @author zhihongp
 * 
 */
public abstract class BaseJob implements Job {
	private static final String CURRENT_USER = "quartzJ_task";
	private static final String IP = "127.0.0.1";

	/**
	 * job具体的业务逻辑
	 * 
	 * @param context
	 * @throws org.quartz.JobExecutionException
	 */
	public abstract void executeJob(JobExecutionContext context) throws JobExecutionException;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		final String dataSource = context.getJobDetail().getJobDataMap().getString(DynamicQuartz.SCHEDULER_DATASOURCE_KEY);
		//LoginUtil.registryUser(CURRENT_USER, IP);
		//RedisSourceSwitcher.setRedisSourceTypeInContext(dataSource);
		//DataSourceSwitcher.setDataSourceTypeInContext(dataSource);

		executeJob(context);
	}
}
