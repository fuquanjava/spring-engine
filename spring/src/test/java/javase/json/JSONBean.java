package javase.json;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * fuquanemail@gmail.com 2016/2/3 16:23
 * description:
 * 1.0.0
 */
public class JSONBean {

    @JSONField(serialize = false)
    private Integer id;

    @JSONField(serialize = false)
    private final Integer id2 = 2;

    private  Integer id3;

    private String name;

    private Date time;

    private List<String> list;

    private Map map;

    public JSONBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Integer getId2() {
        return id2;
    }

    public Integer getId3() {
        return id3;
    }

    public void setId3(Integer id3) {
        this.id3 = id3;
    }
}
