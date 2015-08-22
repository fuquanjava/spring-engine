package vo;

import java.io.Serializable;

/**
 * spring-demo 2015/8/8 22:41
 * fuquanemail@gmail.com
 */
public class StaffVo implements Serializable {

    private String name;

    private String pwd;

    private boolean isLogin = Boolean.FALSE;

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }
}
