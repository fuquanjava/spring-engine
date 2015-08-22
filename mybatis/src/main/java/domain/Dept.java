package domain;

import java.io.Serializable;

/**
 * spring-demo 2015/6/6 18:19
 * fuquanemail@gmail.com
 */
public class Dept implements Serializable {
    private static final long serialVersionUID = -6849794470754667710L;

    private Integer deptno;
    private String dname;
    private String loc;

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
        return this.deptno+","+this.dname+","+this.loc;
    }

    @Override
    public int hashCode() {
        return this.deptno.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Dept)) {
            return false;
        }
        Dept dept = (Dept) obj;
        return dept.deptno == this.deptno;
    }
}