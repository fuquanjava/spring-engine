package spring.orm.mybatis.dao.marker;

import spring.orm.domain.UserDO;

/**
 * fuquanemail@gmail.com 2016/3/29 11:16
 * description: 需要暴露服务 extends BaseMapper
 * 1.0.0
 */

public interface UserDAOMarker extends BaseMapper {

    UserDO findById(int id);
}
