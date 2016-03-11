package system.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import system.core.quartz.QuartzManager;

/**
 * fuquanemail@gmail.com 2016/3/11 15:32
 * description:
 * 1.0.0
 */
@Component("schedeStartService")
public class SchedeStartService implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private QuartzManager quartzManager;

    private volatile boolean initialFlag;

    @Override
    public synchronized void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null && !initialFlag) {
            initialFlag = true;
            if (null != quartzManager) {

                quartzManager.initQuartz();
            }
        }
    }
}
