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
public class SimpleConsumerClient {
    public static void main(String[] args) {
        //public SimpleConsumer(String host, int port, int soTimeout, int bufferSize, String clientId)
        SimpleConsumer consumer = new SimpleConsumer(KafkaProperties.KAFKA_SERVER_URL
                , KafkaProperties.KAFKA_SERVER_PORT
                , KafkaProperties.CONNECTION_TIMEOUT
                , KafkaProperties.KAFKA_PRODUCER_BUFFER_SIZE
                , KafkaProperties.CLIENT_ID);

        System.out.println("Testing single fetch");
        FetchRequest req = new FetchRequestBuilder()
                .clientId(KafkaProperties.CLIENT_ID)
                .addFetch(KafkaProperties.TOPIC, 0, 0L, 100)
                .build();

        FetchResponse fetchResponse = consumer.fetch(req);
        ByteBufferMessageSet messageAndOffset = fetchResponse.messageSet(KafkaProperties.TOPIC, 0);

        for (MessageAndOffset mesage : messageAndOffset) {

            ByteBuffer payload = mesage.message().payload();
            byte[] bytes = new byte[payload.limit()];
            payload.get(bytes);

            try {
                System.out.println(new String(bytes, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }


    }
}
