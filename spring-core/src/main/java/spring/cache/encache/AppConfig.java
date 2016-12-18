//package spring.cache.encache;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurer;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.ehcache.EhCacheCacheManager;
//import org.springframework.cache.interceptor.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//import java.io.IOException;
//
///**
// * fuquanemail@gmail.com 2016/9/26 15:43
// * description:
// */
//@Configuration
//@ComponentScan(basePackages = "spring.cache.service")
//@EnableCaching(proxyTargetClass = true)
//public class AppConfig implements CachingConfigurer {
//
//    private static Logger log = LoggerFactory.getLogger(AppConfig.class);
//
//    @Bean
//    @Override
//    public CacheManager cacheManager() {
//
//        try {
//            log.error("cacheManager invoke.");
//
//            net.sf.ehcache.CacheManager ehcacheCacheManager
//                    = new net.sf.ehcache.CacheManager(new ClassPathResource("ehcache.xml").getInputStream());
//
//            EhCacheCacheManager cacheCacheManager = new EhCacheCacheManager(ehcacheCacheManager);
//            return cacheCacheManager;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Bean
//    @Override
//    public CacheResolver cacheResolver() {
//        log.error("cacheResolver invoke.");
//        return new SimpleCacheResolver(cacheManager());
//    }
//
//    @Bean
//    @Override
//    public KeyGenerator keyGenerator() {
//        log.error("keyGenerator invoke.");
//        return new SimpleKeyGenerator();
//    }
//
//    @Bean
//    @Override
//    public CacheErrorHandler errorHandler() {
//        log.error("errorHandler invoke.");
//        return new SimpleCacheErrorHandler();
//    }
//}
