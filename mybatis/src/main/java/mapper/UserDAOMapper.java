package mapper;

import domain.UserDO;

/**
 * fuquanemail@gmail.com 2016/3/28 13:00
 * description:
 * 1.0.0
 */
public interface UserDAOMapper {

    UserDO findById(Integer id);

    int updateById(UserDO user);
}
