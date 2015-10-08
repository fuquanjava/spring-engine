package com.quartz.task;

import com.quartz.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import quartz.manager.component.QuartzManager;
import quartz.manager.config.QuartzParameter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cdtx on 2015/9/29.
 */
public class QuartzTask1Test extends BaseTest{
    @Autowired(required = false)
    private QuartzManager quartzManager;

    @Test
    public void testQuartz() {
        QuartzParameter config = new QuartzParameter();
        config.setIsRecovery(false);
        config.setIsCronTrigger(true);
        config.setExpression("0 */1 * * * ?");
        config.setJobName("订单统计任务");
        config.setJobGroup("订单任务组");
        config.setJobBeanName("countOrderJob");
        config.setDescription("统计每日的订单总量并且分组");
        config.setTriggerName("每分钟执行的订单统计");
        config.setTriggerGroup("订单触发器组");
        Map<String, String> extraInfo = new HashMap<String, String>();
        extraInfo.put("extra", "test");
        config.setExtraInfo(extraInfo);
        quartzManager.saveOrUpdateJob(config);
        // 返回页面

    }



}
