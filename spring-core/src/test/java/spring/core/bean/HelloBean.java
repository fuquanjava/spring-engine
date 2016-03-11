package spring.core.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * fuquanemail@gmail.com 2016/2/26 17:25
 * description:
 * 1.0.0
 */
@Component
public class HelloBean implements Serializable{

    private String name;

    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HelloBean{");
        sb.append("name='").append(name).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
