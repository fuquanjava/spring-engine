package nosql.redis.operations;

import nosql.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Spring-demo
 * 2015/8/13 14:00
 */
public class RedisList extends BaseTest {
    @Autowired
    protected RedisTemplate redisTemplate;
    @Before
    public void setUp(){

    }
    @After
    public void tearDown(){
        logger.info("down ... down .. down .");

    }

    @Test
    public void t1(){
        ListOperations list  = redisTemplate.opsForList();
        list.leftPush("l1","aaa");


    }

}
