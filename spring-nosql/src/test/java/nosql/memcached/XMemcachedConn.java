package nosql.memcached;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * spring-engine
 * 2015/9/1 15:32
 */
public class XMemcachedConn {
    public static void main(String[] args) {
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(
                AddrUtil.getAddresses("172.16.20.154:11211 172.16.20.168:11211"));
        try {
            MemcachedClient memcachedClient = builder.build();
            //                   key   expireTime  value
            memcachedClient.set("hello", 0,        "Hello,xmemcached");
            String value = memcachedClient.get("hello");


            System.out.println("hello=" + value);
            memcachedClient.delete("hello");
            value = memcachedClient.get("hello");
            System.out.println("hello=" + value);
            //close memcached client
            memcachedClient.shutdown();

        } catch (MemcachedException e) {
            System.err.println("MemcachedClient operation fail");
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.err.println("MemcachedClient operation timeout");
            e.printStackTrace();
        } catch (InterruptedException e) {
            // ignore
        } catch (IOException e) {
            System.err.println("Shutdown MemcachedClient fail");
            e.printStackTrace();
        }



    }
}
