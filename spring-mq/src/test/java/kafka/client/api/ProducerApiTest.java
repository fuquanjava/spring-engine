package kafka.client.api;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.PartitionInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

/**
 * fuquanemail@gmail.com
 */
public class ProducerApiTest {

    Producer<String, String> producer = null;

    String topic = "t2";
    Long timestamp = System.currentTimeMillis();
    String key = "1";
    String value = " producer api test value ";
    Integer partition = 0;


    @Before
    public void setUp() throws Exception {
        testProducerConfig();
    }

    @After
    public void tearDown() throws Exception {
        //Flush any accumulated records from the producer. Blocks until all sends are complete.
        producer.flush();

        producer.close();
    }

    @Test
    public void testProducerConfig() {
        //　初始化属性　ProducerConfig
        Properties props = new Properties();
        //props.put("bootstrap.servers", "10.2.13.71:9091");

        // 可以省略，kafka会自动选择 broker 上的 partition , leader .
        props.put("metadata.broker.list", "192.168.1.71:9092,192.168.1.71:9092");

        props.put("client.id", "DemoProducer");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 指定 partition 类

        // If you include a value for the key but haven't defined a partitioner.class Kafka will use the default partitioner.
        // If the key is null, then the Producer will assign the message to a random Partition
        props.put("partitioner.class", "kafka.client.api.MyPartition");

        props.put("request.required.acks", "1");

        // 初始化  producer , kafkaProducer 是线程安全的，建议在线程间共享。
        producer = new KafkaProducer<>(props);


        List<PartitionInfo> partitionInfos = producer.partitionsFor("t2");
        for (PartitionInfo partition : partitionInfos) {
            System.err.println("partition=" + partition);
        }

    }

    @Test
    public void testSend() {
        // public ProducerRecord(String topic, Integer partition, Long timestamp, K key, V value)
        // 参数: topic , partition, record timestamp , key , value ;
        //  The first is the type of the Partition key, the second the type of the message

        ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, partition, timestamp, key, value);

        // Send the given record asynchronously(异步) and
        // return a future which will eventually contain the response information.

        // send(Record , null) 实现, 异步，立即返回，不会阻塞
        Future<RecordMetadata> future = producer.send(record);
        try {
            RecordMetadata metadata = future.get();
            System.out.println("offset:" + metadata.offset());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendAndCallback() {

        String topic = "t2";
        Long timestamp = System.currentTimeMillis();
        String key = "1";
        String value = " producer api test value ";

        //  The first is the type of the Partition key, the second the type of the message
        ProducerRecord<String, String> record =
                new ProducerRecord<>(topic, partition, timestamp, key, value);

        // Send a record asynchronously(异步) and invoke the given callback when the record
        // has been acknowledged by the server

        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                System.out.println(" ------------acknowledged by the server --------------");
                System.out.println("offset:" + metadata.offset());
            }
        });

        // If you want to simulate a simple blocking call you can do the following:

//        producer.send(new ProducerRecord<byte[],byte[]>("the-topic", "key".getBytes(), "value".getBytes())).get();

    }

    @Test
    public void sendAndCallbackInOrder(){


        //  The first is the type of the Partition key, the second the type of the message
        ProducerRecord<String, String> record1 =
                new ProducerRecord<>(topic, partition, timestamp, key, value);

        ProducerRecord<String, String> record2 =
                new ProducerRecord<>(topic, partition, timestamp, key, value);

//        Callbacks for records being sent to the same partition are guaranteed to execute in order.
//       That is, in the following example callback1 is guaranteed to execute before callback2:

        producer.send(record1, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if(exception != null){
                    exception.printStackTrace();
                }
                System.out.println("The offset of the record 1 we just sent is: " + metadata.offset());
            }
        });


        // broker 是 的callback 是非常快的，不建议在  callback 中执行 阻塞或者 计算昂贵的回调，
        // 否则会阻塞其他线程发送消息，最好用自定义的 executor 来处理
        producer.send(record2, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if(exception != null){
                    exception.printStackTrace();
                }
                System.out.println("The offset of the record 2  we just sent is: " + metadata.offset());
            }
        });


    }
}