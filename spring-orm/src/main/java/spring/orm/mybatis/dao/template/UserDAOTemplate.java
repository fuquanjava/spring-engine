package spring.orm.mybatis.dao.template;

import spring.orm.domain.UserDO;

/**
 * fuquanemail@gmail.com 2016/3/29 11:16
 * description:
 * 1.0.0
 */
public interface UserDAOTemplate {

    UserDO findById(int id);
}
