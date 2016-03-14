package system.core.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import system.core.quartz.config.QuartzConfigServer;
import system.core.util.SpringContextHolder;

import java.util.Map;
import java.util.Set;

/**
 * fuquanemail@gmail.com 2016/3/11 15:33
 * description:
 * 1.0.0
 */
@Component("quartzConfigServerManager")
public class QuartzConfigServerManager {

    @Autowired
    private SchedulerQuartz schedulerQuartz;


    public void initQuartz() {
        System.err.println("开始初始化Quartz");
        Map<String, QuartzConfigServer> configServerMap =
                SpringContextHolder.applicationContext.getBeansOfType(QuartzConfigServer.class);

        if (configServerMap == null || configServerMap.isEmpty()) {
            return;
        }
        Set<Map.Entry<String, QuartzConfigServer>> sets = configServerMap.entrySet();

        QuartzConfigServer configServer = null;
        for (Map.Entry<String, QuartzConfigServer> map : sets) {
            configServer = map.getValue();
        }

        if(null == configServer){
            System.err.println("没有配置Quartz服务");
        }

        schedulerQuartz.setQuartzConfigServer(configServer);

        try {
            schedulerQuartz.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
