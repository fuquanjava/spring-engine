package nosql.redis.transactions;

import dao.User;
import nosql.BaseTest;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * Spring-demo
 * 2015/8/13 11:52
 */
public class RedisTransactions extends BaseTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void t3(){
        Integer value = (Integer) redisTemplate.opsForValue().get("a");
        logger.info("value : {}", value);

    }

    @Test
    public void  t2(){
       Object result = redisTemplate.execute(new RedisCallback() {
           @Override
           public Object doInRedis(RedisConnection connection) throws DataAccessException {
               byte[] b1 = redisTemplate.getStringSerializer().serialize("a");
               byte[] b2 = redisTemplate.getStringSerializer().serialize("b");

               connection.watch(b1 , b2);
               connection.multi();

               byte[] v1 = redisTemplate.getValueSerializer().serialize(1);
               byte[] v2 = redisTemplate.getValueSerializer().serialize(2);

               connection.set(b1, v1);
               connection.set(b2, v2);

               List<Object> list = connection.exec();

               return null;
           }
       });
        System.out.println(result == Boolean.TRUE ? "保存成功":"失败");
    }
    @Test
    public void t1(){
        //开启事务 (坑) , opsForValue 获取的connection 每次都是新的. 不能支持事务
        redisTemplate.multi();
        //执行之后， 客户端可以继续向服务器发送任意多条命令， 这些命令不会立即被执行， 而是被放到一个队列中， 当 EXEC 命令被调用时， 所有队列中的命令才会被执行。

        redisTemplate.opsForValue().set("a" , "a");
        redisTemplate.opsForValue().set("b" ,"b");
        redisTemplate.opsForValue().set("c" ,"c");
        redisTemplate.opsForValue().set("d" , "d");
        List<Object> result = redisTemplate.exec();
        System.out.println(result);

    }
}
