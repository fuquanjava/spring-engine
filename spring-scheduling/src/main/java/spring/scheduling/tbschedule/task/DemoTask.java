package spring.scheduling.tbschedule.task;

import com.taobao.pamirs.schedule.IScheduleTaskDealMulti;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

/**
 * fuquanemail@gmail.com 2016/7/5 15:18
 * description:
 * 1.0.0
 */
@Component("demoTaskBean")
public class DemoTask implements IScheduleTaskDealMulti {

    /**
     * 	执行给定的任务数组。因为泛型不支持new 数组，只能传递OBJECT[]
     * @param tasks 任务数组
     * @param ownSign 当前环境名称
     * @return
     * @throws Exception
     */

    @Override
    public boolean execute(Object[] tasks, String ownSign) throws Exception {

        System.err.println("execute ...");
        return false;
    }

    /**
     * 根据条件，查询当前调度服务器可处理的任务
     * @param taskParameter 任务的自定义参数
     * @param ownSign 当前环境名称
     * @param taskItemNum 当前任务类型的任务队列数量
     * @param taskItemList 当前调度服务器，分配到的可处理队列
     * @param eachFetchDataNum 每次获取数据的数量
     * @return
     * @throws Exception
     */
    @Override
    public List selectTasks(String taskParameter, String ownSign, int taskItemNum, List taskItemList, int eachFetchDataNum) throws Exception {

        System.err.println("selectTasks ...");
        return null;
    }


    /**
     * 获取任务的比较器,主要在NotSleep模式下需要用到
     * @return
     */
    @Override
    public Comparator getComparator() {

        return null;
    }
}
