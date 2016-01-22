package a;

import java.io.Serializable;

/**
 * fuquanemail@gmail.com 2016/1/18 9:56
 * description:
 * 1.0.0
 */
public class User implements Serializable {
    private static final long serialVersionUID = 8988617544798987433L;
    private Integer id;

    private String name;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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

}
