package quartz.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import quartz.manager.source.DynamicCreateQuartzManager;

/**
 * Spring-Engine 2015/10/2 18:17
 * fuquanemail@gmail.com
 */
@Service("seInitializeService")
public class InitializeService implements ApplicationListener<ContextRefreshedEvent> {
    private volatile boolean initialFlag;
    @Autowired(required = false)
    private DynamicCreateQuartzManager dynamicCreateQuartzManager;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (dynamicCreateQuartzManager != null) {
            dynamicCreateQuartzManager.initCreateQuartz();
        }
    }
}
