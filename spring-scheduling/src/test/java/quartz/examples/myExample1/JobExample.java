package quartz.examples.myExample1;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import quartz.examples.myExample1.job.Job1;
import quartz.examples.myExample1.job.Job2;
import quartz.examples.myExample1.job.Job3;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;

/**
 * fuquanemail@gmail.com
 * 2016/5/22
 */
public class JobExample {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job1 = newJob(Job1.class).withIdentity("job1", "group1").build();
        JobDetail job2 = newJob(Job2.class).withIdentity("job2", "group2").build();
        JobDetail job3 = newJob(Job3.class).withIdentity("job3", "group3").build();

        CronTrigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("0/3 * * * * ?"))
                .build();
        CronTrigger trigger2 = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").withSchedule(cronSchedule("0/10 * * * * ?"))
                .build();
        CronTrigger trigger3 = TriggerBuilder.newTrigger().withIdentity("trigger3", "group1").withSchedule(cronSchedule("0/10 * * * * ?"))
                .build();

        sched.scheduleJob(job1, trigger1);
        //sched.scheduleJob(job2, trigger2);
        //sched.scheduleJob(job3, trigger3);

        sched.start();

        try {
            // wait five minutes to show jobs
            Thread.sleep(300L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }
        sched.shutdown(true);
    }

}
