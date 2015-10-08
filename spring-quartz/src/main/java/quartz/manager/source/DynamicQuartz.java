package quartz.manager.source;


import org.apache.commons.lang.StringUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quartz.manager.config.QuartzConfigServer;
import quartz.manager.config.QuartzParameter;
import quartz.manager.job.InitialJob;
import quartz.manager.job.JobProxy;

import java.util.*;
import java.util.Map.Entry;

public class DynamicQuartz {

	public static final String IS_CRON_TRIGGER = "isCronTrigger";

	public static final String TRIGGER_EXPRESSION = "expression";

	public static final String JOB_DESCRIPTION = "description";

	public static final String JOB_BEAN = "jobBeanName";

	public static final String SCHEDULER_DATASOURCE_KEY = "dataSourceKey";

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private Map<String, QuartzConfigServer> targetQuartzConfigServerMap;

	private List<InitialJob> initialJobList;

	private QuartzConfigServer defaultTargetQuartzSource;

	public void setTargetQuartzConfigServerMap(Map<String, QuartzConfigServer> targetQuartzConfigServerMap) {
		this.targetQuartzConfigServerMap = targetQuartzConfigServerMap;
	}

	public void setInitialJobList(List<InitialJob> initialJobList) {
		this.initialJobList = initialJobList;
	}

	public void setDefaultTargetQuartzSource(QuartzConfigServer defaultTargetQuartzSource) {
		this.defaultTargetQuartzSource = defaultTargetQuartzSource;
	}

	public void afterPropertiesSet() throws Exception {
		Set<Entry<String, QuartzConfigServer>> set = targetQuartzConfigServerMap.entrySet();

		for (Entry<String, QuartzConfigServer> entry : set) {
			QuartzConfigServer quartzConfigServer = entry.getValue();

			if (quartzConfigServer != null) {
				Properties quartzProperties = quartzConfigServer.getQuartzProperties();
				Properties p = (Properties) quartzProperties.clone();
				String dataSourceName = quartzProperties.getProperty("org.quartz.jobStore.dataSource");
				/*String dataSourceKey = quartzConfigServer.getDataSourceKey();
				p.setProperty("org.quartz.jobStore.dataSource", dataSourceKey);
				DynamicDataSource dynamicDataSource = (DynamicDataSource) SpringContextHolder.applicationContext.getBean(dataSourceName);
				final DataSource dataSource = dynamicDataSource.getDataSource(dataSourceKey);
				DBConnectionManager.getInstance().addConnectionProvider(dataSourceKey, new ConnectionProvider() {
					@Override
					public Connection getConnection() throws SQLException {
						return DataSourceUtils.doGetConnection(dataSource);
					}

					@Override
					public void shutdown() throws SQLException {
					}

					@Override
					public void initialize() throws SQLException {
					}
				});*/
				StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory(p);
				quartzConfigServer.setStdSchedulerFactory(stdSchedulerFactory);

				if (quartzConfigServer.getAutoStartup()) {
					startQuartz(quartzConfigServer);
				}
			}
		}

		for (InitialJob initialJob : initialJobList) {
			QuartzConfigServer quartzConfigServer = initialJob.getQuartzConfigServer();

			if (quartzConfigServer == null) {
				quartzConfigServer = defaultTargetQuartzSource;
			}

			String configServerKey = quartzConfigServer.getConfigServerKey();
			QuartzParameter config = initialJob.getQuartzParameter();
			saveOrUpdateJob(config, configServerKey);
		}
	}

	public Scheduler getScheduler(String configServerKey) {
		if (configServerKey == null) {
			// configServerKey = QuartzSwitcher.getQuartzType();
		}

		Scheduler scheduler = null;

		try {
			if (StringUtils.isNotBlank(configServerKey)) {
				QuartzConfigServer quartzConfigServer = targetQuartzConfigServerMap.get(configServerKey);

				if (quartzConfigServer != null) {
					StdSchedulerFactory stdSchedulerFactory = quartzConfigServer.getStdSchedulerFactory();
					scheduler = stdSchedulerFactory.getScheduler();
					scheduler.getContext().put("org.quartz.jobStore.dataSource", quartzConfigServer.getDataSourceKey());
				}
			}
		} catch (SchedulerException e) {
			log.error("GetScheduler error", e);
		}

		if (scheduler == null) {
			scheduler = getDefaultScheduler();
		}

		if (scheduler == null) {
			throw new RuntimeException("Can not get a scheduler!");
		}

		return scheduler;
	}

	public void saveOrUpdateJob(QuartzParameter config, String configServerKey) {
		try {
			Scheduler scheduler = getScheduler(configServerKey);
			String dataSourceKey = scheduler.getContext().getString("org.quartz.jobStore.dataSource");

			if (checkJobExists(scheduler, config)) {
				TriggerKey triggerKey = TriggerKey.triggerKey(config.getJobName(), config.getJobGroup());
				Trigger newTrigger = newTrigger(config);
				scheduler.rescheduleJob(triggerKey, newTrigger);
			} else {
				JobDetail jobDetail = newJobDetail(config, dataSourceKey);
				Trigger trigger = newTrigger(config);
				scheduler.scheduleJob(jobDetail, trigger);
			}
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 暂停一个job
	 *
	 * @throws org.quartz.SchedulerException
	 */
	public void pauseTrigger(String triggerName, String triggerGroup, String configServerKey) {
		Scheduler scheduler = getScheduler(configServerKey);

		try {
			scheduler.pauseTrigger(new TriggerKey(triggerName, triggerGroup));
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 恢复job
	 *
	 * @throws org.quartz.SchedulerException
	 */
	public void resumeTrigger(String triggerName, String triggerGroup, String configServerKey) {
		Scheduler scheduler = getScheduler(configServerKey);

		try {
			scheduler.resumeTrigger(new TriggerKey(triggerName, triggerGroup));
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除该config指定的job的trigger
	 * 一个job可能有多个trigger,如果本次删除的trigger是该job唯一的触发器，则该job也会被删除
	 * 
	 */
	public void deleteTrigger(String triggerName, String triggerGroup, String configServerKey) {
		Scheduler scheduler = getScheduler(configServerKey);

		try {
			scheduler.unscheduleJob(new TriggerKey(triggerName, triggerGroup));
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	private Scheduler getDefaultScheduler() {
		Scheduler scheduler = null;

		try {
			StdSchedulerFactory stdSchedulerFactory = defaultTargetQuartzSource.getStdSchedulerFactory();
			scheduler = stdSchedulerFactory.getScheduler();
			scheduler.getContext().put("org.quartz.jobStore.dataSource", defaultTargetQuartzSource.getDataSourceKey());
		} catch (SchedulerException e) {
			log.error("GetDefaultScheduler error", e);
		}

		return scheduler;
	}

	private void startQuartz(QuartzConfigServer quartzConfigServer) throws SchedulerException {
		StdSchedulerFactory stdSchedulerFactory = quartzConfigServer.getStdSchedulerFactory();
		Scheduler scheduler = stdSchedulerFactory.getScheduler();
		scheduler.start();
		log.info("Quartz [" + quartzConfigServer.getConfigServerKey() + "] 自动启动");
	}

	private boolean checkJobExists(Scheduler scheduler, QuartzParameter config) {
		JobKey jobKey = JobKey.jobKey(config.getJobName(), config.getJobGroup());

		try {
			return scheduler.checkExists(jobKey);
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

	private JobDetail newJobDetail(QuartzParameter config, String dataSource) {
		JobDetail jobDetail = JobBuilder.newJob(JobProxy.class).requestRecovery(config.getIsRecovery()).withIdentity(config.getJobName(), config.getJobGroup())
				.usingJobData(JOB_BEAN, config.getJobBeanName()).usingJobData(SCHEDULER_DATASOURCE_KEY, dataSource).build();

		Map<String, String> extraInfoMap = config.getExtraInfo();

		if (extraInfoMap != null) {
			JobBuilder jobBuilder = jobDetail.getJobBuilder();

			for (Entry<String, String> en : extraInfoMap.entrySet()) {
				jobBuilder.usingJobData(en.getKey(), en.getValue());
			}

			jobBuilder.build();
		}

		return jobDetail;
	}

	private Trigger newTrigger(QuartzParameter config) {
		Trigger trigger = null;

		if (config.getIsCronTrigger()) {
			CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(config.getExpression());

			TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger()
					.withIdentity(TriggerKey.triggerKey(config.getTriggerName(), config.getTriggerGroup())).withSchedule(builder)
					.usingJobData(IS_CRON_TRIGGER, config.getIsCronTrigger()).usingJobData(TRIGGER_EXPRESSION, config.getExpression())
					.usingJobData(JOB_DESCRIPTION, config.getDescription());

			if (config.getStartAt() == null || config.getStartAt().getTime() < new Date().getTime()) {
				triggerBuilder.startNow();
			} else {
				triggerBuilder.startAt(config.getStartAt());
			}

			if (config.getEndAt() != null) {
				triggerBuilder.endAt(config.getEndAt());
			}

			trigger = triggerBuilder.build();
		} else {
			SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().repeatForever()
					.withIntervalInSeconds(Integer.valueOf(config.getExpression()));

			TriggerBuilder<SimpleTrigger> triggerBuilder = TriggerBuilder.newTrigger()
					.withIdentity(TriggerKey.triggerKey(config.getTriggerName(), config.getTriggerGroup())).withSchedule(builder)
					.usingJobData(IS_CRON_TRIGGER, config.getIsCronTrigger()).usingJobData(TRIGGER_EXPRESSION, config.getExpression())
					.usingJobData(JOB_DESCRIPTION, config.getDescription());

			if (config.getStartAt() == null) {
				triggerBuilder.startNow();
			} else {
				triggerBuilder.startAt(config.getStartAt());
			}

			if (config.getEndAt() != null) {
				triggerBuilder.endAt(config.getEndAt());
			}

			trigger = triggerBuilder.build();
		}

		return trigger;
	}
}
