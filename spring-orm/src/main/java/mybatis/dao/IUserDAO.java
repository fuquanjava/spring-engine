package mybatis.dao;

import mybatis.dao.domain.UserDO;

/**
 * spring-engine 2015/8/22 15:48
 * fuquanemail@gmail.com
 */
public interface IUserDAO {
    UserDO getUserDO(int id);
}
