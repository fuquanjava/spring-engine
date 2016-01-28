package javase.proxy.support;

import java.io.Serializable;

/**
 * fuquanemail@gmail.com 2016/1/28 9:44
 * description:
 * 1.0.0
 */
public class User implements Serializable {
    private static final long serialVersionUID = -3211094634117271076L;

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
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
