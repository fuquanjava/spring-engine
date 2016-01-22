package rabbitmq.spring.mybatis.mapper;


import rabbitmq.spring.mybatis.annotation.MyBatisMapper;
import rabbitmq.spring.mybatis.domain.User;

import java.util.List;

/**
 * fuquanemail@gamil.com
 * Date: 14-7-16 下午10:32
 */

@MyBatisMapper
public interface UserMapper {

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteById(int id);

    public User findById(int id);

    public List<User> findAll();

    public User getUser(int id);

    public User listUser();
}
