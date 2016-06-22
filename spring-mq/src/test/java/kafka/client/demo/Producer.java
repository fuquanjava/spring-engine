package kafka.client.demo;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * fuquanemail@gmail.com 2016/6/21 17:35
 * description:
 * 1.0.0
 */
public class Producer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9093");
        props.put("client.id", "DemoProducer");
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        org.apache.kafka.clients.producer.Producer<Integer, String> producer = new KafkaProducer(props);
        String topic = "test";
        Integer key = 1;
        String value = "value";

        Future<RecordMetadata>  future = producer.send(new ProducerRecord<>(topic,key,value));
        try {
            RecordMetadata metadata = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.err.println("send msg ok");

    }
}
