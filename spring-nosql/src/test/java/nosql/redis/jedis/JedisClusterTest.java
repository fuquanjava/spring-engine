package nosql.redis.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * fuquanemail@gmail.com
 */
public class JedisClusterTest {
    private static final int DEFAULT_TIMEOUT = 2000;
    private static final int DEFAULT_REDIRECTIONS = 3;
    private static final JedisPoolConfig DEFAULT_CONFIG = new JedisPoolConfig();

    @Test
    public void testDiscoverNodesAutomatically() throws Exception {

        Set<HostAndPort> jedisClusterNode = new HashSet<>();
        jedisClusterNode.add(new HostAndPort("192.168.1.128", 7000));

        JedisCluster cluster = new JedisCluster(jedisClusterNode, DEFAULT_TIMEOUT);

        System.out.println(cluster.getClusterNodes().size());

        Map<String, JedisPool> jedisPoolMap = cluster.getClusterNodes();

        Set<String> keySet = jedisPoolMap.keySet();

        Iterator<String> iterable = keySet.iterator();

        while (iterable.hasNext()){
            String key = iterable.next();
            JedisPool pool = jedisPoolMap.get(key);

            System.out.println("key="+ key +", pool="+ pool);
        }

        cluster.set("a","1234");


        String value = cluster.get("a");

        System.out.println(value);

    }
}
