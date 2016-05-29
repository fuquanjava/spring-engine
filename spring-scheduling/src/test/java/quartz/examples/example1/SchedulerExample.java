package quartz.examples.example1;


import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchedulerExample {
    static Logger log = LoggerFactory.getLogger(SimpleExample.class);

    public static void main(String[] args) throws SchedulerException {


        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        log.info("------- Initialization Complete -----------");

        log.info(" ------- start schedul all jobs ,listener, plgins ...");

        sched.start();

        sched.shutdown();

    }
}
