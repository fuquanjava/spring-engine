package quartz.manager.component;


import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import quartz.manager.config.QuartzParameter;
import quartz.manager.source.DynamicQuartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author zhihongp
 */
public class QuartzManager {

    private DynamicQuartz dynamicQuartz;

    public void setDynamicQuartz(DynamicQuartz dynamicQuartz) {
        this.dynamicQuartz = dynamicQuartz;
    }

    /**
     * 创建一个job，如果已存在该job，则更新该job的触发器
     *
     * @param config
     */
    public void saveOrUpdateJob(QuartzParameter config) {
        dynamicQuartz.saveOrUpdateJob(config, null);
    }

    /**
     * 暂停一个job
     *
     * @throws org.quartz.SchedulerException
     */
    public void pauseTrigger(String triggerName, String triggerGroup) {
        dynamicQuartz.pauseTrigger(triggerName, triggerGroup, null);
    }

    /**
     * 恢复job
     *
     * @throws org.quartz.SchedulerException
     */
    public void resumeTrigger(String triggerName, String triggerGroup) {
        dynamicQuartz.resumeTrigger(triggerName, triggerGroup, null);
    }

    /**
     * 删除该config指定的job的trigger
     * 一个job可能有多个trigger,如果本次删除的trigger是该job唯一的触发器，则该job也会被删除
     */
    public void deleteTrigger(String triggerName, String triggerGroup) {
        dynamicQuartz.deleteTrigger(triggerName, triggerGroup, null);
    }

    public List<QuartzParameter> getAllJobs() {
        List<QuartzParameter> list = new ArrayList<QuartzParameter>();
        try {
            Scheduler scheduler = getScheduler();
            Set<JobKey> keys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());

            if (keys == null) {
                return list;
            }

            for (JobKey key : keys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(key);

                if (triggers != null) {
                    JobDetail jobDetail = scheduler.getJobDetail(key);

                    for (Trigger trigger : triggers) {
                        list.add(getQuartzParameter(scheduler, jobDetail, trigger));
                    }
                }
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public QuartzParameter getJob(String jobName, String jobGroup, String triggerName, String triggerGroup) {
        Scheduler scheduler = getScheduler();
        JobKey jobKey = new JobKey(jobName, jobGroup);
        TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroup);

        try {
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            Trigger trigger = scheduler.getTrigger(triggerKey);

            if (jobDetail == null || trigger == null) {
                return null;
            }

            return getQuartzParameter(scheduler, jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    private QuartzParameter getQuartzParameter(Scheduler scheduler, JobDetail jobDetail, Trigger trigger) throws SchedulerException {
        QuartzParameter quartzParameter = new QuartzParameter();
        String jobBeanName = jobDetail.getJobDataMap().getString(DynamicQuartz.JOB_BEAN);

        if (jobBeanName == null) {
            throw new RuntimeException("Quartz jobBeanName is null");
        }

        quartzParameter.setJobBeanName(jobBeanName);
        quartzParameter.setJobName(jobDetail.getKey().getName());
        quartzParameter.setJobGroup(jobDetail.getKey().getGroup());
        quartzParameter.setTriggerName(trigger.getKey().getName());
        quartzParameter.setTriggerGroup(trigger.getKey().getGroup());
        quartzParameter.setIsCronTrigger(trigger.getJobDataMap().getBoolean(DynamicQuartz.IS_CRON_TRIGGER));
        quartzParameter.setExpression(trigger.getJobDataMap().getString(DynamicQuartz.TRIGGER_EXPRESSION));
        quartzParameter.setStatus(scheduler.getTriggerState(trigger.getKey()).toString());
        quartzParameter.setIsRecovery(jobDetail.requestsRecovery());
        quartzParameter.setDescription(trigger.getJobDataMap().getString(DynamicQuartz.JOB_DESCRIPTION));
        quartzParameter.setPrevFireTime(trigger.getPreviousFireTime());
        quartzParameter.setNextFireTime(trigger.getNextFireTime());
        return quartzParameter;
    }

    private Scheduler getScheduler() {
        return dynamicQuartz.getScheduler(null);
    }

}
