package kafka.client.api;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.TopicPartition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * fuquanemail@gmail.com
 */
public class ConsumerApiTest {

    // 消除了0.8.x High Api 跟 Low Api 之间的区别. 透明的处理broker的失败，partition.
    // consumer group 之间的 负载 , 会保持 TCP 跟 broker的连接，关闭失败会泄露连接，不是线程安全的。！！！
    KafkaConsumer consumer1 = null;

    KafkaConsumer consumer2 = null;

    KafkaConsumer consumer3 = null;


    KafkaProducer<String, String> producer = null;

    @Test
    public void init1() {
        Properties props = new Properties();
        // "10.2.13.71:9091,10.2.13.71:9092,10.2.13.71:9093"; 如果只配置一个，那么当配置的broker挂掉以后，客户端不能连接到集群了.
        props.put("bootstrap.servers", KafkaConstant.KAFKA_BROKER_COMPANY);
        props.put("group.id", "test1");
        // enable.auto.commit = true　表示 在 auto.commit.interval.ms 频率内自动提交
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer1 = new KafkaConsumer<>(props);

    }
    @Test
    public void init2() {
        Properties props = new Properties();
        // "10.2.13.71:9091,10.2.13.71:9092,10.2.13.71:9093"; 如果只配置一个，那么当配置的broker挂掉以后，客户端不能连接到集群了.
        props.put("bootstrap.servers", KafkaConstant.KAFKA_BROKER_COMPANY);
        props.put("group.id", "test2");
        // enable.auto.commit = true　表示 在 auto.commit.interval.ms 频率内自动提交
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer2 = new KafkaConsumer<>(props);

    }

    @Test
    public void init3() {
        Properties props = new Properties();
        // "10.2.13.71:9091,10.2.13.71:9092,10.2.13.71:9093"; 如果只配置一个，那么当配置的broker挂掉以后，客户端不能连接到集群了.
        props.put("bootstrap.servers", KafkaConstant.KAFKA_BROKER_COMPANY);
        props.put("group.id", "test3");
        // enable.auto.commit = true　表示 在 auto.commit.interval.ms 频率内自动提交
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer3 = new KafkaConsumer<>(props);

    }


    @Test
    public void initProducer() {
        //　初始化属性　ProducerConfig
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaConstant.KAFKA_BROKER_COMPANY);

        // 可以省略，kafka会自动选择 broker 上的 partition , leader .
        props.put("metadata.broker.list", KafkaConstant.KAFKA_BROKER_COMPANY);

        props.put("client.id", "producerRunner");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 指定 partition 类

        // If you include a value for the key but haven't defined a partitioner.class Kafka will use the default partitioner.
        // If the key is null, then the Producer will assign the message to a random Partition
        //props.put("partitioner.class", "kafka.client.api.MyPartition");

        //props.put("request.required.acks", "1");

        // 初始化  producer , kafkaProducer 是线程安全的，建议在线程间共享。
        producer = new KafkaProducer<>(props);
    }

    @Before
    public void setUp() throws Exception {
        // init();
    }


    @Test
    public void testMultiThreadProducer(){

        initProducer();

        KafkaProducerRunner runner1= new KafkaProducerRunner(producer," p-1","t2",0);
        KafkaProducerRunner runner2= new KafkaProducerRunner(producer," p-2","t2",1);
        KafkaProducerRunner runner3= new KafkaProducerRunner(producer," p-3","t2",2);

        runner1.start();
        runner2.start();
        runner3.start();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testMultiThreadedProcessing() {

        // 注意 初始化 的 group.id
        init1();
        init2();
        init3();

        KafkaConsumerRunner runner1 = new KafkaConsumerRunner(consumer1, " c-1");
        KafkaConsumerRunner runner2 = new KafkaConsumerRunner(consumer2, " c-2");
        KafkaConsumerRunner runner3 = new KafkaConsumerRunner(consumer3, " c-3");

        runner1.start();
        runner2.start();
        runner3.start();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void testManualPartitionAssignment() {
        String topic = KafkaConstant.KAFKA_TOPIC_COMPANY;
        TopicPartition partition0 = new TopicPartition(topic, 0);
        //TopicPartition partition1 = new TopicPartition(topic, 1);
        consumer1.assign(Arrays.asList(partition0));

        printConsumer();

    }

    public void printConsumer() {
        while (true) {

            // 客户端通过 发送心跳 来让 broker 感知客户端存活，心跳时间 consumer.poll( 心跳时间 )
            // 心跳 超过 这个时间 props.put("session.timeout.ms", "30000"); 就会认为客户端死了，partition 就会交给别的线程
            ConsumerRecords<String, String> records = consumer1.poll(1500);
            for (ConsumerRecord<String, String> record : records) {
                int partition = record.partition();
                System.err.println("partition = " + partition);
                System.err.println("offset = " + record.offset());
                System.err.println("key = " + record.key());
                System.err.println("value = " + record.value());
            }
        }
    }


    /**
     * 手动控制 偏移量
     */
    @Test
    public void testManualOffsetControl() {
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaConstant.KAFKA_BROKER_COMPANY);
        props.put("group.id", "test");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(KafkaConstant.KAFKA_TOPIC_COMPANY));
        final int minBatchSize = 3;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                buffer.add(record);
            }
            if (buffer.size() >= minBatchSize) {

                //insertIntoDb(buffer);
                System.err.println("保存数据库 开始....");
                try {
                    System.err.println("buffer ======================= " + buffer);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println("保存数据库 成功....");
                // which will block until the offsets have been successfully committed or fatal error has happened during the commit process
                // consumer.commitSync();

                // which is non-blocking and will trigger OffsetCommitCallback upon either successfully committed or fatally failed.
                consumer.commitAsync(new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                        System.err.println("commit callback !");
                    }
                });


                buffer.clear();
            }
        }


    }

    @Test
    public void testSubscribe() {

        //  client is subscribing to the topics t7 and t6 as part of a group of consumers called test as described above.
        //consumer.subscribe(Arrays.asList("t2", "t1"));

        consumer1.subscribe(Arrays.asList("t2"));

        printConsumer();

    }

    @After
    public void tearDown() throws Exception {
        consumer1.close();

    }
}