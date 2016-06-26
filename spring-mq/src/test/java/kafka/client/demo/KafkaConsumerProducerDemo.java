package kafka.client.demo;

/**
 * fuquanemail@gmail.com
 */
public class KafkaConsumerProducerDemo {
    public static void main(String[] args) {
        boolean isAsync = true;
        Producer producerThread = new Producer(KafkaProperties.T6, isAsync);
        producerThread.start();
        //producerThread.sendSynMessage();

        //Consumer consumerThread = new Consumer(KafkaProperties.T6);
        //consumerThread.start();

        //consumerThread.subscribeMessage();

    }
}