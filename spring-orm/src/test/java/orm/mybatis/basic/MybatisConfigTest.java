package orm.mybatis.basic;

import mybatis.dao.IUserDAO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import orm.BaseTest;

/**
 * spring-engine 2015/8/22 16:03
 * fuquanemail@gmail.com
 */
public class MybatisConfigTest extends BaseTest {
    @Autowired
    IUserDAO userDAO;

    @Test
    public void config(){
        String className = userDAO.getClass().getSimpleName();
        logger.info(className);
    }


}
