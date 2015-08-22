package spring.mybatis.dao;



import spring.mybatis.domain.User;

import java.util.List;

/**
 * fuquanemail@gamil.com
 * Date: 14-7-16 下午10:32
 */

public interface IUserDao {
    public void addUser(User user) ;

    public void updateUser(User user);

    public void deleteById(int id);

    public User findById(int id);


    public List<User> findAll();

    public User getUser(int id);

    public User listUser();
}
