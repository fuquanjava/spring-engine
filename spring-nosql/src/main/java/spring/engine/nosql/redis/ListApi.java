package spring.engine.nosql.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * fuquanemail@gmail.com 2016/9/5 14:34
 * description:
 */
@Service("listApi")
public class ListApi {
    @Autowired
    RedisTemplate redisTemplate;

    private static final String LIST_QUEUE = "queue";


    /**
     * 假如在指定时间内没有任何元素被弹出，则返回一个 nil 和等待时长。
        反之，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。
     * @param consumer
     */
    public void brpop(final String consumer) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {

                List<byte[]> list = redisConnection.bRPop(0, redisTemplate.getDefaultSerializer().serialize(LIST_QUEUE));

                for (byte[] bytes : list) {
                    String s = (String) redisTemplate.getDefaultSerializer().deserialize(bytes);
                    System.err.println(consumer+ ":" + s);
                }
                return null;
            }
        });
    }

    public void lpush(final String value) {

        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {

                connection.lPush(redisTemplate.getDefaultSerializer().serialize(LIST_QUEUE),

                        redisTemplate.getDefaultSerializer().serialize(value));

                return Boolean.TRUE;
            }
        });
    }

}
