package kafka.client.demo;


import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.javaapi.FetchResponse;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.MessageAndOffset;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * fuquanemail@gmail.com
 */
public class SimpleConsumerDemo {
    private static void printMessages(ByteBufferMessageSet messageSet) throws UnsupportedEncodingException {
        for (MessageAndOffset messageAndOffset : messageSet) {
            ByteBuffer payload = messageAndOffset.message().payload();
            byte[] bytes = new byte[payload.limit()];
            payload.get(bytes);
            System.out.println(new String(bytes, "UTF-8"));
        }
    }

    private static void generateData() {
        Producer producer2 = new Producer(KafkaProperties.T6, false);
        producer2.start();
        Producer producer3 = new Producer(KafkaProperties.T6, false);
        producer3.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        //generateData();

        // SimpleConsumer(String host, int port, int soTimeout, int bufferSize, String clientId)
        SimpleConsumer simpleConsumer = new SimpleConsumer(KafkaProperties.KAFKA_SERVER_URL,
                KafkaProperties.KAFKA_SERVER_PORT,
                KafkaProperties.CONNECTION_TIMEOUT,
                KafkaProperties.KAFKA_PRODUCER_BUFFER_SIZE,
                KafkaProperties.CLIENT_ID);

        System.out.println("Testing single fetch");
        FetchRequest req = new FetchRequestBuilder()
                .clientId(KafkaProperties.CLIENT_ID)
                .addFetch(KafkaProperties.T6, 0, 0L, 100)
                .build();
        FetchResponse fetchResponse = simpleConsumer.fetch(req);
        printMessages(fetchResponse.messageSet(KafkaProperties.T6, 0));

        /*System.out.println("Testing single multi-fetch");
        Map<String, List<Integer>> topicMap = new HashMap<>();
        topicMap.put(KafkaProperties.T6, Collections.singletonList(0));
        topicMap.put(KafkaProperties.T6, Collections.singletonList(0));
        req = new FetchRequestBuilder()
                .clientId(KafkaProperties.CLIENT_ID)
                .addFetch(KafkaProperties.T6, 0, 0L, 100)
                .addFetch(KafkaProperties.T6, 0, 0L, 100)
                .build();
        fetchResponse = simpleConsumer.fetch(req);
        int fetchReq = 0;
        for (Map.Entry<String, List<Integer>> entry : topicMap.entrySet()) {
            String topic = entry.getKey();
            for (Integer offset : entry.getValue()) {
                System.out.println("Response from fetch request no: " + ++fetchReq);
                printMessages(fetchResponse.messageSet(topic, offset));
            }
        }*/
    }
}
