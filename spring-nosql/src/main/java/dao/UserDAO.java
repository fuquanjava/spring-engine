package dao;

/**
 * spring-demo 2015/7/18 23:47
 * fuquanemail@gmail.com
 */
public interface UserDAO {
    void save(User user);

    User getUser(Integer userId);
}
