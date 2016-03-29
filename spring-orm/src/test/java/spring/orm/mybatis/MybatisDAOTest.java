package spring.orm.mybatis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import spring.orm.BaseOrmTest;
import spring.orm.domain.UserDO;
import spring.orm.mybatis.dao.template.UserDAOTemplate;

/**
 * fuquanemail@gmail.com 2016/3/29 11:54
 * description:
 * 1.0.0
 */
public class MybatisDAOTest extends BaseOrmTest {

    @Autowired
    private UserDAOTemplate userDAOTemplate;

    /**
     * 开启查询缓存
     * mybatis 缓存: 1. sqlSession级别。 2.application
     * 下面演示的是 sqlSession.
     *
     * application级别的缓存 需要添加第三方缓存框架，oscache ,encache等。
     *
     */
    @Test
    public void findById(){
        // 1. 执行sql
        UserDO userDO = userDAOTemplate.findById(1);
        System.err.println(userDO);

        // 2. hit cache, 不执行sql
        userDO = userDAOTemplate.findById(1);
        System.err.println(userDO);

        try {
            // 修改数据
            System.err.println("开始休眠5s");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // hit cache , 不会重新查询数据，造成数据不统一。
        userDO = userDAOTemplate.findById(1);
        System.err.println(userDO);
    }
}
