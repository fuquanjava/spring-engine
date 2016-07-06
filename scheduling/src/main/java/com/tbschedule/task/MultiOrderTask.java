package com.tbschedule.task;

import com.alibaba.fastjson.JSON;
import com.taobao.pamirs.schedule.IScheduleTaskDealMulti;
import com.taobao.pamirs.schedule.TaskItemDefine;
import com.tbschedule.BaseTask;
import com.tbschedule.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * tbschedule 2015/10/5 23:58
 * fuquanemail@gmail.com
 */
@Component("multiOrderTask")
public class MultiOrderTask extends BaseTask implements IScheduleTaskDealMulti<Order> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean execute(Order[] tasks, String ownSign) throws Exception {
        logger.error("******* OrderTask2 execute ******* start *******");
        logger.error("ownSign ={}", ownSign);
        logger.error("tasks ={}", JSON.toJSONString(tasks));

        logger.error("******* OrderTask2 execute ******* end *******");
        return false;
    }

    @Override
    public List<Order> selectTasks(String taskParameter, String ownSign,
                                   int taskItemNum, List<TaskItemDefine> taskItemList,
                                   int eachFetchDataNum) throws Exception {

        logger.error("*******OrderTask2 ******* selectTasks******* start*******");

        logger.error("taskParameter= {}", taskParameter);
        logger.error("ownSign = {}", ownSign);
        logger.error("taskQueueNum ={}", taskItemNum);
        logger.error("taskItemList ={}", JSON.toJSONString(taskItemList.size()));
        logger.error("eachFetchDataNum= {}", eachFetchDataNum);


        List<Order> list1 = new ArrayList<Order>();
        list1.add(new Order());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.error("******* OrderTask2 ******* selectTasks*******end*******");
        return list1;
    }

    @Override
    public Comparator<Order> getComparator() {
        return null;
    }
}
