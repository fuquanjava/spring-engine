package nosql.redis.pipeline;

import nosql.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Spring-demo
 * 2015/8/14 15:02
 */
public class RedisPipeline extends BaseTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void t1(){
       redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                // executePipelined 已经处理过open , close
                // connection.openPipeline();
                // connection.closePipeline();

                for(int i= 0 ; i<10000; i++){
                    final String v = Integer.toString(i);
                    connection.set(redisTemplate.getKeySerializer().serialize(v),
                            redisTemplate.getValueSerializer().serialize(v));
                }
                return null;
            }
        });

        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                boolean isPipelined = connection.isPipelined();
                logger.info(isPipelined ? "isPipelined":"not pipelined" );
                try {
                    if(isPipelined){
                        for(int i= 0 ; i<10000; i++){
                            final String v = Integer.toString(i);
                            connection.set(redisTemplate.getKeySerializer().serialize(v),
                                    redisTemplate.getValueSerializer().serialize(v));
                        }
                    }
                }catch (Exception e){

                }finally {
                    connection.closePipeline();
                }
                return null;
            }
        });

    }
}
