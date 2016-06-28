package kafka.client.api;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;

/**
 * fuquanemail@gmail.com 2016/6/28 10:59
 * description:
 * 1.0.0
 */
public class KafkaProducerRunner extends Thread {
    // thread safe, shared in mulit threads
    private KafkaProducer<String, String> producer;

    private String name;

    private String topic;

    private Integer partition;

    public KafkaProducerRunner(KafkaProducer<String, String> producer, String name, String topic, Integer partition) {
        this.topic = topic;
        this.producer = producer;
        this.name = name;
        this.partition = partition;
    }

    @Override
    public void run() {
        long id = 1;
        String value =  "the msg is send by thread-"+ name + " for partition- " + partition ;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String key = "id =" + id + "-" + Integer.toString(partition);
            id++;

            //  The first is the type of the Partition key, the second the type of the message
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, partition, key, value);

            try {
                RecordMetadata metadata = producer.send(record).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }
}
