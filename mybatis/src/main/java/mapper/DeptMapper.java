package mapper;

import domain.Dept;

import java.util.List;

/**
 * spring-demo 2015/6/6 18:26
 * fuquanemail@gmail.com
 */
public interface DeptMapper {
    void addDept(Dept dept);

    void updateDept(Dept dept);

    public Dept findById(int deptno);

    public void deleteById(int deptno);

    public List<Dept> findAll();
}
