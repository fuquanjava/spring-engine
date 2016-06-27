package kafka.client.api;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.javaapi.FetchResponse;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.MessageAndOffset;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * fuquanemail@gmail.com 2016/6/27 16:14
 * description:
 * 1.0.0
 */
public class SimpleConsumerApi {
    public static void main(String[] args)  {
        // public SimpleConsumer(String host, int port, int soTimeout, int bufferSize, String clientId)
        String host = "10.2.13.71";
        int port = 9091;
        int soTimeout = 100000;
        int bufferSize = 1024 * 10;
        String clientId = "simpleConsumerApi";

        SimpleConsumer simpleConsumer = new SimpleConsumer(host, port, soTimeout, bufferSize, clientId);
        FetchRequest request = new FetchRequestBuilder()
                .addFetch("t2", 0, 0, 10)
                .clientId(clientId)
                .build();

        FetchResponse response = simpleConsumer.fetch(request);
        System.err.println("hashError:"+response.hasError());

        ByteBufferMessageSet messageSet = response.messageSet("t2",0);

        for (MessageAndOffset messageAndOffset : messageSet) {
            ByteBuffer payload = messageAndOffset.message().payload();
            byte[] bytes = new byte[payload.limit()];
            payload.get(bytes);

            try {
                System.err.println(new String(bytes, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

    }
}
