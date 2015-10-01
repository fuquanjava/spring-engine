package com.quartz.job;

import com.quartz.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * fuquanemail@gmail.com
 * 2015/9/29 15:43
 */
public class HelloJob implements Job{

    private static Logger LOG = LoggerFactory.getLogger(HelloJob.class);

    /**
     * Job，Job需要一个公有的构造函数，否则Factory无法构建
     */
    public HelloJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOG.info(DateUtil.getNow());
    }
}
