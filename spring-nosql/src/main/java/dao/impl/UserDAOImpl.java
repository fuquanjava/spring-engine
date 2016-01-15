package dao.impl;

import dao.User;
import dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * spring-demo 2015/7/18 23:49
 * fuquanemail@gmail.com
 */
public class UserDAOImpl implements UserDAO {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void save(final User user) {
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {

                redisConnection.set(redisTemplate.getKeySerializer().serialize(user.getUserId()),
                        redisTemplate.getValueSerializer().serialize(user) );

                return true;
            }
        });
    }

    @Override
    public User getUser(final Integer userId) {
        redisTemplate.execute(new RedisCallback<User>() {
            @Override
            public User doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte [] key = redisTemplate.getKeySerializer().serialize(userId);
                if(redisConnection.exists(key)){
                    byte [] values = redisConnection.get(key);
                    User user = (User) redisTemplate.getValueSerializer().deserialize(values);
                    return user;
                }
                return null;
            }
        });
        return null;
    }
}
