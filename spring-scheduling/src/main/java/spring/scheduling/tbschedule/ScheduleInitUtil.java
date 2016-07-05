package spring.scheduling.tbschedule;

import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * fuquanemail@gmail.com 2016/7/5 15:18
 * description:
 * 1.0.0
 */
@Component("scheduleInitUtil")
public class ScheduleInitUtil implements InitializingBean, ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(ScheduleInitUtil.class);

    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, String> zkConfig = new HashMap<String, String>();
        zkConfig.put("zkConnectString", "127.0.0.1:2181");
        zkConfig.put("rootPath", "/schedule/test");
        zkConfig.put("zkSessionTimeout", "60000");
        zkConfig.put("userName", "zookeeper");
        zkConfig.put("password", "zookeeper");
        zkConfig.put("isCheckParentPath", "true");

        TBScheduleManagerFactory tbscheduleManagerFactory = new TBScheduleManagerFactory();
        tbscheduleManagerFactory.setApplicationContext(context);
        tbscheduleManagerFactory.setZkConfig(zkConfig);
        tbscheduleManagerFactory.init();

        logger.warn("TBBPM 成功启动schedule调度引擎 ...");
    }
}