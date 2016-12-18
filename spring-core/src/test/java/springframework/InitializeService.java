package springframework;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * fuquanemail@gmail.com
 * 2015/9/30 15:38
 */
@Service("initializeService")
public class InitializeService implements ApplicationListener<ContextRefreshedEvent> {

    private volatile boolean initFlag = false;

    @Override
    public synchronized void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
           //防止容器初始化多次
        if(contextRefreshedEvent.getApplicationContext().getParent() == null && !initFlag){
            initFlag = true;
            // 所有的bean都实例化完毕之后会调用
            System.out.println("InitializeService onApplicationEvent,事件源:"+contextRefreshedEvent.getSource());
            System.out.println("容器名称:"+contextRefreshedEvent.getApplicationContext().getDisplayName());

        }

    }
}
