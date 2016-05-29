package quartz.examples.myExample1.job;

import org.apache.commons.lang.time.DateFormatUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import uitl.DateUtil;

import java.util.Date;

/**
 * fuquanemail@gmail.com
 * 2016/5/22
 */
public class Job3 implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("job---3--- execute----" + DateFormatUtils.format(new Date(), DateUtil.LONG_DATE_FORMAT_STR));

    }
}
