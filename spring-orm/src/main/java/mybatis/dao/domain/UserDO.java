package mybatis.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * spring-engine
 * 2015/9/2 12:55
 */
public class UserDO implements Serializable {
    private Integer id;

    private String name;

    private String password;

    private Date lastLoginTime;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
