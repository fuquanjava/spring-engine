package spring.cache;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.cache.domain.User;
import spring.cache.service.AppConfig;
import spring.cache.service.UserService;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-1
 * <p>Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserServiceTest {
    private static Logger log = LoggerFactory.getLogger(UserServiceTest.class);


    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    private Cache userCache;

    @Before
    public void setUp() {
        userCache = cacheManager.getCache("user");
    }


    @Test
    public void testCache() {
        Long id = 1L;
        User user = new User(id, "zhang", "zhang@gmail.com");
        userService.save(user);
        user = (User) userCache.get(id).get();
        log.error("user:"+ user);
        Assert.assertNotNull(user);
        userService.findById(id);
    }

}
