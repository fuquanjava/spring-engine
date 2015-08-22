package dao.redis;

/**
 * spring-demo 2015/7/19 16:16
 * fuquanemail@gmail.com
 */
public interface IRedisClientTemplate {

    public void disconnect() ;

    public String set(String key, String value) ;

    public String get(String key) ;

}
