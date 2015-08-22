package nosql.redis;

import nosql.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.*;

/**
 * Spring-demo
 * 2015/8/13 14:22
 */
public class RedisKeys extends BaseTest {
    ValueOperations<String,Object> valueOperations;
    @Autowired
    protected RedisTemplate redisTemplate;

    @Before
    public void setUp(){

        valueOperations =  redisTemplate.opsForValue();
    }

    @Test
    public void t4(){
        int max = Math.max((int) (10/.75f) + 1, 16);
        System.out.println((int) (10/.75f));
        System.out.println(max);

        Set<Integer> set = new HashSet<>();

        if(set.size() == 1 && set.iterator().next() == 1){
            System.out.println(set.iterator().next());
        }
        System.out.println("=================================================");
        set.add(1);
        set.add(2);
        Iterator<Integer> ite = set.iterator();
        while(ite.hasNext()){
            Integer i  = ite.next();
            if(i == 1){
                //ite.remove();
                set.remove(i);
            }
        }
        System.out.println(set);


    }
    public void f(){
        return;
    }


    @Test
    public void t1(){
        //保存
        valueOperations.set("a", "a");
        valueOperations.set("b","b");

        //删除
        //redisTemplate.delete("a");
        List<String> keys = new ArrayList<>();
        keys.add("a");
        keys.add("b");
        redisTemplate.delete(keys);


    }

    @Test
    public void t2(){
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte [] key = redisTemplate.getKeySerializer().serialize("c");
                byte [] value = redisTemplate.getValueSerializer().serialize("c");
                connection.set(key, value);
                connection.expire(key,10);
                connection.set(key, redisTemplate.getValueSerializer().serialize("cccc"));
                return true;
            }
        });
        //valueOperations.set("c","c", 10, TimeUnit.SECONDS);
        //valueOperations.set("c","c");

        for(int i = 0 ; i< 5 ; i++){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String c = (String) valueOperations.get("c");
            logger.info(c);

        }
    }

    @Test
    public void t3(){

        List<String> keys = new ArrayList<>();
        keys.add("a");
        keys.add("b");


        valueOperations.set("a","1111");
        valueOperations.set("b","1111");

        List<Object> list = valueOperations.multiGet(keys);
        System.out.println(list);


        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(redisTemplate.getKeySerializer().serialize("a"), redisTemplate.getValueSerializer().serialize("aaa"));
                connection.set(redisTemplate.getKeySerializer().serialize("b"), redisTemplate.getValueSerializer().serialize("bbb"));


                List<byte[]> values = connection.mGet(redisTemplate.getKeySerializer().serialize("a"), redisTemplate.getKeySerializer().serialize("b"));
                for(int i = 0; i <values.size(); i++){
                    byte [] value = values.get(i);
                    logger.info("value = {} ", redisTemplate.getValueSerializer().deserialize(value));
                }

                return null;
            }
        });

    }
}
