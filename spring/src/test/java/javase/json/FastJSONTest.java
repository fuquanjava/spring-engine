package javase.json;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * fuquanemail@gmail.com 2016/2/3 16:21
 * description:
 * 1.0.0
 */
public class FastJSONTest {


    @Test
    public void ignoreField(){
        JSONBean bean = new JSONBean();
        bean.setId(1);
        bean.setName("bean");
        bean.setTime(new Date());
        bean.setId3(3);

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        Map<Integer, String> map = Maps.newHashMap();
        map.put(1, "m");
        map.put(2, "n");


        bean.setList(list);
        bean.setMap(map);

        String json = JSON.toJSONString(bean);
        System.err.println("json:" + json);
    }
}
