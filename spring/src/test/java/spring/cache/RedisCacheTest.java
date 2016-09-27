package spring.cache;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import spring.BaseTest;
import spring.cache.domain.User;
import spring.cache.service.UserService;

/**
 * fuquanemail@gmail.com 2016/9/27 11:25
 * description:
 */
public class RedisCacheTest extends BaseTest{

    @Autowired
    private UserService userService;

    @Test
    public void testSave(){
        Long id = 1L;
        User user = new User(id, "zhang", "zhang@gmail.com");
        userService.save(user);

        userService.save(user);

        user = new User(2L, "zhang", "zhang@gmail.com");
        userService.save(user);

    }

    @Test
    public void testFindById() throws Exception {
        User user = userService.findById(1L);
        logger.info(user.toString());
    }
}
