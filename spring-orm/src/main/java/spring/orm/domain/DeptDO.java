package spring.orm.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * fuquanemail@gmail.com 2016/3/30 11:59
 * description:
 * 1.0.0
 */
public class DeptDO implements Serializable {

    private Long id;

    private String deptName;

    private Long parentId;

    private Integer level;

    private String deptDesc;

    private Integer status;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeptDO{");
        sb.append("id=").append(id);
        sb.append(", deptName='").append(deptName).append('\'');
        sb.append(", parentId=").append(parentId);
        sb.append(", level=").append(level);
        sb.append(", deptDesc='").append(deptDesc).append('\'');
        sb.append(", status=").append(status);
        sb.append(", createUser='").append(createUser).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser='").append(updateUser).append('\'');
        sb.append(", updateTime=").append(updateTime);
        sb.append('}');
        return sb.toString();
    }
}
