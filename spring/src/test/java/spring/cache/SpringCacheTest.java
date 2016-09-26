package spring.cache;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import spring.BaseTest;
import spring.cache.domain.User;

import java.io.IOException;

/**
 * fuquanemail@gmail.com 2016/9/26 15:23
 * description:
 */
public class SpringCacheTest extends BaseTest{

    /**
     * 基于编码式的
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        //创建底层Cache
        net.sf.ehcache.CacheManager ehcacheManager
                = new net.sf.ehcache.CacheManager(new ClassPathResource("ehcache.xml").getInputStream());

        //创建Spring的CacheManager
        EhCacheCacheManager cacheCacheManager = new EhCacheCacheManager();
        //设置底层的CacheManager
        cacheCacheManager.setCacheManager(ehcacheManager);

        Long id = 1L;
        User user = new User(id, "zhang", "zhang@gmail.com");

        //根据缓存名字获取Cache
        Cache cache = cacheCacheManager.getCache("user");
        //往缓存写数据
        cache.put(id, user);
        //从缓存读数据
        Assert.assertNotNull(cache.get(id, User.class));
    }


    /**
     * 基于xml
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        Long id = 2L;
        User user = new User(id, "zhang", "zhang@gmail.com");

        //根据缓存名字获取Cache
        Cache cache = cacheCacheManager.getCache("user");
        //往缓存写数据
        cache.put(id, user);
        //从缓存读数据
        Assert.assertNotNull(cache.get(id, User.class));
    }

    /**
     * 基于redis
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        Long id = 3L;
        User user = new User(id, "zhang", "zhang@gmail.com");

        //根据缓存名字获取Cache
        Cache cache = redisCacheManager.getCache("user");
        //往缓存写数据
        cache.put(id, user);
        //从缓存读数据
        Assert.assertNotNull(cache.get(id, User.class));
    }


    @Autowired(required = false)
    private EhCacheCacheManager cacheCacheManager;

    @Autowired(required = false)
    private RedisCacheManager redisCacheManager;

    @Autowired(required = false)
    private RedisTemplate redisTemplate;
}
