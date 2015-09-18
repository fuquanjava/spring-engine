package mybatis.dao;

import mybatis.dao.domain.DeptDO;
import mybatis.dao.domain.DeptQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * spring-engine
 * 2015/9/2 13:02
 */
public interface IDeptDAO {

    DeptDO getDeptDO(int id);

    List<DeptDO> listDept(@Param("deptQuery")DeptQuery deptQuery);

    List<DeptDO> listDept2(@Param("ids")List<Integer> ids);


    List<DeptDO> listDept3(@Param("map")Map map);
}
