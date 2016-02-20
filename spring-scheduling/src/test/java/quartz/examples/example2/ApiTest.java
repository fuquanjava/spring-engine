package quartz.examples.example2;

import com.spring.scheduling.quartz.job.HelloJob;
import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;
import org.quartz.DateBuilder;

import java.util.Date;

/**
 * spring-engine 2016/2/20 17:20
 * fuquanemail@gmail.com
 */
public class ApiTest {
    static final String DATA_FORMAT = "yyyy-MM-dd hh:mm:ss";


    @Test
    public void dateBuilder() {
        Date date = new Date();

        // 如果指定时间为null,就是当前时间
        Date d1 = DateBuilder.nextGivenSecondDate(date, 10);
        Date d2 = DateBuilder.nextGivenSecondDate(null, 10);

        System.err.println(DateFormatUtils.format(date, DATA_FORMAT));
        System.err.println(DateFormatUtils.format(d1, DATA_FORMAT));
        System.err.println(DateFormatUtils.format(d2, DATA_FORMAT));


    }
    @Test
    public void newInstance() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        HelloJob helloJob = HelloJob.class.newInstance();
        System.err.println(helloJob.getClass().getName());
        Class<?> clazz = Class.forName("com.spring.scheduling.quartz.job.HelloJob");

        helloJob = (HelloJob) clazz.newInstance();
        System.err.println(helloJob.getClass().getName());
    }

}
