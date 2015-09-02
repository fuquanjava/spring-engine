package mybatis.dao.domain;

import java.io.Serializable;

/**
 * spring-engine
 * 2015/9/2 12:56
 */
public class DeptDO implements Serializable {
    private Integer id;

    private Integer deptno;

    private String dname;

    private String loc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "DeptDO{" +
                "id=" + id +
                ", deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
