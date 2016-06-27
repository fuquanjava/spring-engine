package kafka.client.api;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * fuquanemail@gmail.com 2016/6/27 15:42
 * description:
 * 1.0.0
 */
public class MyPartition implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        System.err.println("MyPartition topic:" + topic);
        System.err.println("MyPartition key:"   + key);
        System.err.println("MyPartition value:" + value);

        return 0;
    }

    @Override
    public void close() {
        System.err.println("MyPartition close () ");
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
