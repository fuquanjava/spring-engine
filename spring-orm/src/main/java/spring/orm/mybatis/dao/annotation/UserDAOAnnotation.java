package spring.orm.mybatis.dao.annotation;

import spring.orm.domain.UserDO;

/**
 * fuquanemail@gmail.com 2016/3/29 11:16
 * description:
 * 1.0.0
 */
@MyBatisMapper
public interface UserDAOAnnotation {

    UserDO findById(int id);
}
