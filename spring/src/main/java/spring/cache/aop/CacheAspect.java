package spring.cache.aop;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * fuquanemail@gmail.com 2016/9/27 21:24
 * description:
 */

@Component
public class CacheAspect {

    private static Logger log = LoggerFactory.getLogger(CacheAspect.class);


    private RedisTemplate redisTemplate;

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 添加缓存
     *
     * @param pjp
     * @param cache
     * @return
     * @throws Throwable
     */
    @Around("@annotation(cache)")
    public Object cacheable(final ProceedingJoinPoint pjp, Cache cache) throws Throwable {

        String key = cache.key();

        //使用redisTemplate操作缓存
        @SuppressWarnings("unchecked")
        ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();

        Object value = valueOper.get(key); // 从缓存获取数据
        if (value != null) {
            return value; // 如果有数据,则直接返回
        }

        value = pjp.proceed();

        log.info("cachePut, key={}", key);

        // 缓存,到后端查询数据
        if (cache.expire() <= 0) { // 如果没有设置过期时间,则无限期缓存
            valueOper.set(key, value);
        } else {
            // 否则设置缓存时间
            valueOper.set(key, value, cache.expire(), TimeUnit.SECONDS);
        }
        return value;
    }

    @Around("@annotation(evict)")
    public Object cacheEvict(final ProceedingJoinPoint pjp, Evict evict) throws Throwable {
        Object value;
        // 执行方法
        value = pjp.proceed();

        //单个key操作
        if (StringUtils.isNotBlank(evict.key())) {
            redisTemplate.delete(evict.key());
        }

        //批量key操作
        if (evict.keys() != null && evict.keys().length > 0) {
            for (String keyname : evict.keys()) {
                redisTemplate.delete(keyname);
            }
        }
        return value;
    }

}
