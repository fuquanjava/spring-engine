package spring.api.bean;

import org.springframework.stereotype.Component;

/**
 * fuquanemail@gmail.com
 * 2015/9/30 15:21
 */
@Component(value = "bean1")
public class Bean1 implements Bean{
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
