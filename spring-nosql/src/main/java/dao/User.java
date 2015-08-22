package dao;

import java.io.Serializable;

/**
 * spring-demo 2015/7/18 23:48
 * fuquanemail@gmail.com
 */
public class User implements Serializable {

    private static final long serialVersionUID = 7110275440135292814L;

    private Integer userId;

    private String name;

    public Integer getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public User(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public User() {
    }

    @Override
    public String toString() {
        return this.getUserId()+","+this.getName();
    }
}

