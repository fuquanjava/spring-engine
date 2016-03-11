package system.core.quartz.config;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * fuquanemail@gmail.com 2016/3/11 16:09
 * description:
 * 1.0.0
 */
public class QuartzParameter implements Serializable {

    /**
     * sched名称
     */
    private String schedName;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务组
     */
    private String jobGroup;

    /**
     * 任务的具体类名, 可以是全路径的java类, 也可以是被spring管理的简单类名
     */
    private String jobClassName;

    /**
     * 任务的描述
     */
    private String description;

    /**
     * 任务中断（如断电）是否是重新执行
     */
    private boolean isRecovery;

    /**
     * 触发器的名称（如：每天2点触发库存对账）
     */
    private String triggerName;

    /**
     * 触发器分组
     */
    private String triggerGroup;

    /**
     * 是否是cron的表达式
     */
    private boolean isCronTrigger;

    /**
     * 任务触发时间的表达式(isCronTrigger等于true时为cron的表达式，isCronTrigger等于false时为间隔时间，单位秒)
     */
    private String expression;

    /**
     * 上次触发时间
     */
    private Date prevFireTime;

    /**
     * 下次触发时间
     */
    private Date nextFireTime;

    /**
     * 任务开始时间
     */
    private Date startAt;

    /**
     * 任务结束时间
     */
    private Date endAt;

    /**
     * 触发器状态
     */
    private String triggerStatus;

    /**
     * 自定义扩展信息
     */
    private Map<String, String> extraInfo;

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRecovery() {
        return isRecovery;
    }

    public void setRecovery(boolean recovery) {
        isRecovery = recovery;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public boolean isCronTrigger() {
        return isCronTrigger;
    }

    public void setCronTrigger(boolean cronTrigger) {
        isCronTrigger = cronTrigger;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Date getPrevFireTime() {
        return prevFireTime;
    }

    public void setPrevFireTime(Date prevFireTime) {
        this.prevFireTime = prevFireTime;
    }

    public Date getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public String getTriggerStatus() {
        return triggerStatus;
    }

    public void setTriggerStatus(String triggerStatus) {
        this.triggerStatus = triggerStatus;
    }

    public Map<String, String> getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(Map<String, String> extraInfo) {
        this.extraInfo = extraInfo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuartzParameter { ");
        sb.append("schedName='").append(schedName).append('\'');
        sb.append(", jobName='").append(jobName).append('\'');
        sb.append(", jobGroup='").append(jobGroup).append('\'');
        sb.append(", jobClassName='").append(jobClassName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", isRecovery=").append(isRecovery);
        sb.append(", triggerName='").append(triggerName).append('\'');
        sb.append(", triggerGroup='").append(triggerGroup).append('\'');
        sb.append(", isCronTrigger=").append(isCronTrigger);
        sb.append(", expression='").append(expression).append('\'');
        sb.append(", prevFireTime=").append(prevFireTime);
        sb.append(", nextFireTime=").append(nextFireTime);
        sb.append(", startAt=").append(startAt);
        sb.append(", endAt=").append(endAt);
        sb.append(", triggerStatus='").append(triggerStatus).append('\'');
        sb.append(", extraInfo=").append(extraInfo);
        sb.append('}');
        return sb.toString();
    }
}
