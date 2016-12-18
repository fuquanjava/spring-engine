package spring.cache.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import spring.cache.domain.User;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserService {

    static Logger log = LoggerFactory.getLogger(UserService.class);

    Set<User> users = new HashSet<User>();

    /**
     * CachePut: 即应用到移除数据的方法上，如删除方法，调用方法时会从缓存中移除相应的数据：
     *
     * @param user
     * @return
     */
    @CachePut(value = "user", key = "#user.id")
    public User save(User user) {

        User u = findById(user.getId());
        if (u != null) {
            log.error("user exists, {}", user.toString());
        } else {
            users.add(user);
        }
        return user;
    }

    /**
     * CachePut 必须要有返回值才能缓存
     * org.springframework.cache.interceptor.CacheAspectSupport 357
     * <p>
     * cachePutRequest.apply(result.get());
     *
     * @CachePut(value = "user", key = "#user.id")
     * public void save(User user) {
     * users.add(user);
     * }
     */

    @CachePut(value = "user", key = "#user.id")
    public User update(User user) {
        users.remove(user);
        users.add(user);
        return user;
    }

    @CacheEvict(value = "user", key = "#user.id")
    public User delete(User user) {
        users.remove(user);
        return user;
    }

    @CacheEvict(value = "user", allEntries = true)
    public void deleteAll() {
        users.clear();
    }

    /**
     * Cacheable:应用到读取数据的方法上，即可缓存的方法，如查找方法：先从缓存中读取，如果没有再调用方法获取数据，然后把数据添加到缓存中：
     *
     * @param id
     * @return
     */
    @Cacheable(value = "user", key = "#id")
    public User findById(final Long id) {
        System.out.println("cache miss, invoke find by id, id:" + id);
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Cacheable(value = "user", key = "100")
    public Set<User> findAll() {
        System.out.println("find all 111!");

        if (users == null || users.size() == 0) {
            System.out.println("find all");
            users.add(new User(3L, "zs", "zs@123.com"));
        } else {

            System.out.println("find all 22!");

            return users;
        }

        return users;
    }

}
