package orm.mybatis.basic;

import mybatis.dao.IDeptDAO;
import mybatis.dao.IUserDAO;
import mybatis.dao.domain.DeptDO;
import mybatis.dao.domain.DeptQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import orm.BaseTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * spring-engine 2015/8/22 16:03
 * fuquanemail@gmail.com
 */
public class MybatisConfigTest extends BaseTest {
    @Autowired(required = false)
    IUserDAO userDAO;

    @Autowired
    IDeptDAO deptDAO;

    @Test
    public void config(){
        DeptQuery query = new DeptQuery();
        List<Integer> ids= new ArrayList<>();
        ids.add(2);
        //List<DeptDO> deptDOList = deptDAO.listDept(query);

        //List<DeptDO> deptDOList = deptDAO.listDept2(null);
        Map map = new HashMap<>();
        map.put("ids", ids);
        List<DeptDO> deptDOList = deptDAO.listDept3(map);


    }
}
