package orm.mybatis.basic;

import mybatis.dao.IDeptDAO;
import mybatis.dao.IUserDAO;
import mybatis.dao.domain.DeptDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import orm.BaseTest;

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
        DeptDO deptDO = deptDAO.getDeptDO(2);
        logger.info("dept= {} ",deptDO);

    }
}
