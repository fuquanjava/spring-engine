package kafka.client.api;


import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * fuquanemail@gmail.com
 */
public class ProducerApi {


    public static void main(String[] args) {

        //　初始化属性　ProducerConfig
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.2.13.71:9091");
        props.put("client.id", "DemoProducer");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 指定 partition 类
        props.put("partitioner.class", "kafka.client.api.MyPartition");

        // 初始化  producer
        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        List<PartitionInfo> partitionInfos = producer.partitionsFor("t2");
        for (PartitionInfo partition : partitionInfos) {
            System.err.println("partition=" + partition);
        }

        // public ProducerRecord(String topic, Integer partition, Long timestamp, K key, V value)
        // 参数: topic , partition, record timestamp , key , value ;
        String topic = "t2";
        Integer partition = 0;
        Long timestamp = System.currentTimeMillis();
        String key = "1";
        String value = " producer api test value ";

        ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, partition, timestamp, key, value);

        // Send the given record asynchronously(异步) and
        // return a future which will eventually contain the response information.
        Future<RecordMetadata> future = producer.send(record);
        try {
            RecordMetadata metadata = future.get();
            System.out.println("offset:" + metadata.offset());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // Send a record and invoke the given callback when the record
        // has been acknowledged by the server

        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                System.out.println(" ------------acknowledged by the server --------------");
                System.out.println("offset:" + metadata.offset());
            }
        });


        //Flush any accumulated records from the producer. Blocks until all sends are complete.
        producer.flush();

        producer.close();
    }


}
