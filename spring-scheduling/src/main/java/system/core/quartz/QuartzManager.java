package system.core.quartz;

import org.apache.commons.collections.CollectionUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;
import system.core.quartz.config.QuartzParameter;

import java.util.*;

/**
 * fuquanemail@gmail.com 2016/3/14 9:09
 * description:
 * 1.0.0
 */
@Service("quartzManager")
public class QuartzManager {

    private Scheduler scheduler;

    public static final String IS_CRON_TRIGGER = "isCronTrigger";

    public static final String TRIGGER_EXPRESSION = "expression";

    public static final String JOB_CLASS_NAME = "jobClassName";

    public static final String SCHEDULER_DATASOURCE_KEY = "dataSourceKey";


    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Scheduler getScheduler() {
        if (scheduler == null) {
            throw new RuntimeException("Scheduler 没有初始化");
        }
        return scheduler;
    }

    public String getSchedulerName() throws Exception {
        return getScheduler().getSchedulerName();
    }


    public List<QuartzParameter> getAllJobs() throws SchedulerException {
        Set<JobKey> jobKeySet = getScheduler().getJobKeys(GroupMatcher.anyJobGroup());
        if (CollectionUtils.isEmpty(jobKeySet)) {
            return new ArrayList<>(0);
        }
        List<QuartzParameter> list = new ArrayList<>();
        for (JobKey jobKey : jobKeySet) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);

            if (triggers != null) {
                JobDetail jobDetail = getScheduler().getJobDetail(jobKey);

                for (Trigger trigger : triggers) {
                    QuartzParameter quartzParameter = getQuartzParameter(scheduler, jobDetail, trigger);
                    list.add(quartzParameter);
                }
            }
        }
        return list;
    }

    public boolean saveJob(String schedName, String jobName, String jobGroup, String jobClassName, String description, boolean isRecovery, String triggerName,
                           String triggerGroup, boolean isCronTrigger, String expression, Date startAt, Date endAt, Map<String, String> extraInfo) {
        QuartzParameter config = creatQuartzParameter(schedName, jobName, jobGroup, jobClassName, description, isRecovery, triggerName, triggerGroup,
                isCronTrigger, expression, startAt, endAt, extraInfo);
        saveOrUpdateJob(config, true);
        return true;
    }

    private QuartzParameter creatQuartzParameter(String schedName, String jobName, String jobGroup, String jobClassName, String description,
                                                 boolean isRecovery, String triggerName, String triggerGroup, boolean isCronTrigger, String expression, Date startAt, Date endAt,
                                                 Map<String, String> extraInfo) {
        if (isCronTrigger) {
            try {
                new CronExpression(expression);
            } catch (Exception e) {
                throw new RuntimeException("Save a quartz job error, expression is not a cron", e);
            }
        } else {
            try {
                Integer.valueOf(expression);
            } catch (Exception e) {
                throw new RuntimeException("Save a quartz job error, expression is not a number", e);
            }
        }

        QuartzParameter quartzParameter = new QuartzParameter();
        quartzParameter.setSchedName(schedName);
        quartzParameter.setJobName(jobName);
        quartzParameter.setJobGroup(jobGroup);
        quartzParameter.setJobClassName(jobClassName);
        quartzParameter.setDescription(description);
        quartzParameter.setRecovery(isRecovery);
        quartzParameter.setTriggerName(triggerName);
        quartzParameter.setTriggerGroup(triggerGroup);
        quartzParameter.setCronTrigger(isCronTrigger);
        quartzParameter.setExpression(expression);
        quartzParameter.setStartAt(startAt);
        quartzParameter.setEndAt(endAt);
        quartzParameter.setExtraInfo(extraInfo);
        return quartzParameter;
    }

    private QuartzParameter getQuartzParameter(Scheduler scheduler, JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        String jobClassName = jobDataMap.getString(JOB_CLASS_NAME);

        if (jobClassName == null) {
            throw new RuntimeException("Quartz jobClassName is null");
        }

        Map<String, String> extraInfo = null;
        Set<Map.Entry<String, Object>> set = jobDataMap.entrySet();

        if (!set.isEmpty()) {
            extraInfo = new HashMap<String, String>();

            for (Map.Entry<String, Object> entry : set) {
                String key = entry.getKey();
                Object value = entry.getValue();
            }
        }

        QuartzParameter quartzParameter = new QuartzParameter();
        quartzParameter.setSchedName(scheduler.getSchedulerName());
        quartzParameter.setJobName(jobDetail.getKey().getName());
        quartzParameter.setJobGroup(jobDetail.getKey().getGroup());
        quartzParameter.setJobClassName(jobClassName);
        quartzParameter.setDescription(jobDetail.getDescription());
        quartzParameter.setRecovery(jobDetail.requestsRecovery());
        quartzParameter.setTriggerName(trigger.getKey().getName());
        quartzParameter.setTriggerGroup(trigger.getKey().getGroup());
        quartzParameter.setPrevFireTime(trigger.getPreviousFireTime());
        quartzParameter.setNextFireTime(trigger.getNextFireTime());
        quartzParameter.setStartAt(trigger.getStartTime());
        quartzParameter.setEndAt(trigger.getEndTime());
        quartzParameter.setTriggerStatus(scheduler.getTriggerState(trigger.getKey()).toString());
        quartzParameter.setExtraInfo(extraInfo);
        quartzParameter.setCronTrigger(trigger.getJobDataMap().getBoolean(IS_CRON_TRIGGER));
        quartzParameter.setExpression(trigger.getJobDataMap().getString(TRIGGER_EXPRESSION));

        return quartzParameter;
    }


    public void saveOrUpdateJob(QuartzParameter config, boolean alreadyWarning) {
        try {
            Scheduler scheduler = getScheduler();
            //　这个是以前放进去了，所以这里可以取出来 SchedulerQuartz的92行
            String dataSourceKey = scheduler.getContext().getString("org.quartz.jobStore.dataSource");
            JobKey jobKey = JobKey.jobKey(config.getJobName(), config.getJobGroup());
            TriggerKey triggerKey = TriggerKey.triggerKey(config.getTriggerName(), config.getTriggerGroup());

            boolean isExists = scheduler.checkExists(jobKey);

            if (isExists) {
                if (alreadyWarning) {
                    throw new RuntimeException("The job[schedName=" + scheduler.getSchedulerName() + ",jobName=" + config.getJobName() + ",jobGroup="
                            + config.getJobGroup() + "] is already exist");
                }
            }

            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(jobKey);
            JobDetail jobDetail = newJobDetail(config, dataSourceKey);
            Trigger trigger = newTrigger(config);
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    private Class<Job> getJobClass(QuartzParameter config) {
        String jobClassName = config.getJobClassName();
        try {
            return (Class<Job>) Class.forName(jobClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    private JobDetail newJobDetail(QuartzParameter config, String dataSource) {
        Class<Job> jobClass = getJobClass(config);
        if (null == jobClass) {
            throw new RuntimeException("Job class is null");
        }

        JobDetail jobDetail = JobBuilder.newJob(getJobClass(config)).requestRecovery(config.isRecovery()).withIdentity(config.getJobName(), config.getJobGroup())
                .withDescription(config.getDescription()).usingJobData(JOB_CLASS_NAME, config.getJobClassName())
                .usingJobData(SCHEDULER_DATASOURCE_KEY, dataSource).build();

        Map<String, String> extraInfoMap = config.getExtraInfo();
        JobBuilder jobBuilder = jobDetail.getJobBuilder();

        if (extraInfoMap != null) {
            for (Map.Entry<String, String> en : extraInfoMap.entrySet()) {
                jobBuilder.usingJobData(en.getKey(), en.getValue());
            }
        }

        jobDetail = jobBuilder.build();
        return jobDetail;
    }

    private Trigger newTrigger(QuartzParameter config) {
        Trigger trigger = null;

        if (config.isCronTrigger()) {
            CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(config.getExpression()).withMisfireHandlingInstructionDoNothing();
            TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger()
                    .withIdentity(TriggerKey.triggerKey(config.getTriggerName(), config.getTriggerGroup())).withSchedule(builder)
                    .usingJobData(IS_CRON_TRIGGER, config.isCronTrigger()).usingJobData(TRIGGER_EXPRESSION, config.getExpression());

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
                    .usingJobData(IS_CRON_TRIGGER, config.isCronTrigger()).usingJobData(TRIGGER_EXPRESSION, config.getExpression());

            if (config.getStartAt() == null || config.getStartAt().getTime() < new Date().getTime()) {
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
