package kafka.client.api;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * fuquanemail@gmail.com
 */
public class ConsumerApiTest {

    // 消除了0.8.x High Api 跟 Low Api 之间的区别. 透明的处理broker的失败，partition.
    // consumer group 之间的 负载 , 会保持 TCP 跟 broker的连接，关闭失败会泄露连接，不是线程安全的。！！！
    KafkaConsumer consumer = null;

    @Test
    public void init() {
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaConstant.host + ":" + KafkaConstant.port);
        props.put("group.id", "test");
        // enable.auto.commit = true　表示 在 auto.commit.interval.ms 频率内自动提交
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);

    }

    @Before
    public void setUp() throws Exception {
        init();
    }

    @Test
    public void testSubscribe() {

        //  client is subscribing to the topics t7 and t6 as part of a group of consumers called test as described above.
        consumer.subscribe(Arrays.asList("t7", "t6"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1500);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @After
    public void tearDown() throws Exception {

    }
}