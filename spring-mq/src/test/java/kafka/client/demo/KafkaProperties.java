package kafka.client.demo;

/**
 * fuquanemail@gmail.com
 */
public interface KafkaProperties {

    String T6 = "t6";
    String KAFKA_SERVER_URL = "192.168.1.128";
    int KAFKA_SERVER_PORT = 9091;
    int KAFKA_PRODUCER_BUFFER_SIZE = 64 * 1024;
    int CONNECTION_TIMEOUT = 100000;
    String CLIENT_ID = "SimpleConsumerClient";
}
