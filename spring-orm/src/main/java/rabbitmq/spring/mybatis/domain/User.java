package rabbitmq.spring.mybatis.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * fuquanemail@gamil.com
 */
public class User implements  Serializable{
    private static final long serialVersionUID = 8160066602222958933L;

    private long  id ;

    private String name;

    private String password;

    private String email ;

    private Date lastlogintime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof User)){
            return false;
        }
        User user = (User) o;
        return user.id == this.id;
    }

    @Override
    public int hashCode() {
        // bug ...
        return (int)id;
    }

    @Override
    public String toString() {
        return this.name+","+this.password+","+this.email+","+this.lastlogintime;
    }
}
