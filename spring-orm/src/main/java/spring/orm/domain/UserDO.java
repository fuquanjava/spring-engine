package spring.orm.domain;

import java.io.Serializable;
import java.util.Date;

public class UserDO implements Serializable {

    private static final long serialVersionUID = 5240886521960295418L;

    private Long id;

    private String username;

    private String password;

    private String realname;

    private String tel;

    private Integer status;

    private Integer locked;

    private Date lastLoginTime;

    private String lastLoginIp;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String remark;

    // -------- 额外关联的字段----------------
    private DeptDO deptDO;

    public DeptDO getDeptDO() {
        return deptDO;
    }

    public void setDeptDO(DeptDO deptDO) {
        this.deptDO = deptDO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDO{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", realname='").append(realname).append('\'');
        sb.append(", tel='").append(tel).append('\'');
        sb.append(", status=").append(status);
        sb.append(", locked=").append(locked);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", lastLoginIp='").append(lastLoginIp).append('\'');
        sb.append(", createUser='").append(createUser).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser='").append(updateUser).append('\'');
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", deptDO=").append(deptDO);
        sb.append('}');
        return sb.toString();
    }
}