package spring.orm.xa;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import spring.orm.domain.UserDO;

import javax.annotation.Resource;

/**
 * fuquanemail@gmail.com 2016/3/30 11:50
 * description: 简化dao,不用接口
 * 1.0.0
 */
@Repository("userDao")
public class UserDAO {

    @Resource(name = "sqlSessionTemplateUser")
    private SqlSessionTemplate sqlSessionTemplateUser;

    public int updateById(UserDO userDO) {
        return sqlSessionTemplateUser.update("userDAO.updateById", userDO);
    }

    public UserDO findById(int id) {
        return sqlSessionTemplateUser.selectOne("userDAO.findById", id);
    }

}
