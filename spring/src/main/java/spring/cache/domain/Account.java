package spring.cache.domain;

import java.io.Serializable;

/**
 * fuquanemail@gmail.com 2016/9/26 9:20
 * description:
 */
public class Account implements Serializable {

    private int id;
    private String name;

    public Account(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account [");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(']');
        return sb.toString();
    }
}
