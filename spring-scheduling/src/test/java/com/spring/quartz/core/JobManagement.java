package com.spring.quartz.core;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * spring-engine 2016/2/25 22:55
 * fuquanemail@gmail.com
 */
public class JobManagement {

    /**
     * 计划任务map
     */
    private static Map<String, ScheduleJob> jobMap = new ConcurrentHashMap<>();

    static {
        for (int i = 0; i < 5; i++) {
            ScheduleJob job = new ScheduleJob();
            job.setJobId("1001" + i);
            job.setJobName("jobName-" + i);
            job.setJobGroup("jobGroup-1");
            job.setJobStatus("1");
            job.setCronExpression("0/3 * * * * ?");
            job.setDesc("测试任务任务");
            addJob(job);
        }
    }

    /**
     * 添加任务
     *
     * @param scheduleJob
     */
    public static void addJob(ScheduleJob scheduleJob) {
        jobMap.put(scheduleJob.getJobGroup() + "_" + scheduleJob.getJobName(), scheduleJob);
    }

    public static List<ScheduleJob> getAllJobs() {
        Collection<ScheduleJob> collection = jobMap.values();
        return Lists.newArrayList(collection);
    }
}
