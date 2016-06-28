package kafka.client.api;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * fuquanemail@gmail.com 2016/6/28 10:40
 * description:
 * 1.0.0
 */
public class KafkaConsumerRunner extends Thread {

    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final KafkaConsumer consumer;
    private final String threadName;

    public KafkaConsumerRunner(KafkaConsumer consumer, String threadName) {
        this.consumer = consumer;
        this.threadName = threadName;
    }

    public void run() {
        try {
            consumer.subscribe(Arrays.asList(KafkaConstant.KAFKA_TOPIC_COMPANY));
            while (!closed.get()) {
                ConsumerRecords records = consumer.poll(10000);
                if (records.isEmpty()) {
                    System.err.println("ConsumerRecords  is empty !");
                    continue;
                }

                Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
                while (iterator.hasNext()) {
                    ConsumerRecord<String, String> record = iterator.next();
                    System.err.println(threadName + "---partition=" + record.partition() + "," +
                            " key = {" + record.key() + "}, value = {" + record.value() + "}");

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        } catch (WakeupException e) {

            if (!closed.get()) {
                e.printStackTrace();
            }
        } finally {
            consumer.close();
        }
    }

    // Shutdown hook which can be called from a separate thread
    public void shutdown() {
        closed.set(true);
        consumer.wakeup();
    }
}
