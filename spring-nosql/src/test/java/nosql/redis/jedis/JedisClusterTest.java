package nosql.redis.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.util.JedisClusterCRC16;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JedisClusterTest {

    static Logger log = LoggerFactory.getLogger(JedisClusterTest.class);

    JedisCluster cluster = null;

    @Before
    public void jedisCluster() {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.1.128", 7000));
        nodes.add(new HostAndPort("192.168.1.128", 7001));
        nodes.add(new HostAndPort("192.168.1.128", 7002));
        nodes.add(new HostAndPort("192.168.1.128", 7003));
        nodes.add(new HostAndPort("192.168.1.128", 7004));
        nodes.add(new HostAndPort("192.168.1.128", 7005));


        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(2);
        poolConfig.setMaxTotal(5);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setMaxWaitMillis(6 * 1000);

        // public JedisCluster(Set<HostAndPort> set, final GenericObjectPoolConfig poolConfig) {}
        cluster = new JedisCluster(nodes, poolConfig);

        System.out.println(cluster);

        Map<String, JedisPool> poolMap = cluster.getClusterNodes();

        Iterator<String> iterator = poolMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            JedisPool pool = poolMap.get(key);
            System.out.println("key:" + key + ", JedisPool:" + pool);

        }
    }

    @Test
    public void set() {
        int slot = JedisClusterCRC16.getSlot("a");
        log.info("slot:{}", slot);

        String ok = cluster.set("a", "123");
        log.info("response is ok ? {}", ok);

        String value = cluster.get("a");
        log.info("value :{}", value);


    }


    @Test
    public void test() {
        int num = 1000;
        String key = "key.";
        String value = "value.";
        while (true) {
            for (int i = 1; i <= num; i++) {
                try {
                    // 存数据
                    String ok = cluster.set(key + i, value + i);
                    log.info("set {} is ok ? : {}", key + i, ok);

                    // 取数据
                    String rsp = cluster.get(key + i);

                    log.info("get {} value : {}", key + i, rsp);

                    if (StringUtils.isEmpty(value)) {
                        log.error("===>break" + key + i + " value is null");
                        break;
                    }
                } catch (Exception e) {
                    log.error("====>", e);
                    continue;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
