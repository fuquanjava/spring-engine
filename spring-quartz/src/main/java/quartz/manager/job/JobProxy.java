package quartz.manager.job;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import quartz.manager.SpringContextHolder;
import quartz.manager.source.DynamicQuartz;

public class JobProxy implements Job {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String jobBeanName = context.getJobDetail().getJobDataMap().getString(DynamicQuartz.JOB_BEAN);

		if (jobBeanName == null) {
			throw new JobExecutionException("jobBeanName is null !");
		}

		Job job = null;

		try {
			job = (Job) SpringContextHolder.applicationContext.getBean(jobBeanName, Job.class);
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}

		job.execute(context);
	}
}