package spring.quartz.core;

import spring.quartz.job.JobInfo;

import java.util.ArrayList;
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
    private static Map<String, JobInfo> jobMap = new ConcurrentHashMap<>();

    static {
        for (int i = 0; i < 5; i++) {
            JobInfo job = new JobInfo();
            job.setJobId("1001" + i);
            job.setJobName("job-name-" + i);
            job.setJobGroup("group-1");
            job.setJobStatus("1");
            job.setCronExpression("0/5 * * * * ?");
            job.setDesc("测试任务任务");
            addJob(job);
        }
    }

    /**
     * 添加任务
     *
     * @param scheduleJob
     */
    public static void addJob(JobInfo scheduleJob) {
        jobMap.put(scheduleJob.getJobGroup() + "_" + scheduleJob.getJobName(), scheduleJob);
    }

    public static List<JobInfo> getAllJobs() {
        Collection<JobInfo> collection = jobMap.values();
        return new ArrayList<>(collection);
    }
}
