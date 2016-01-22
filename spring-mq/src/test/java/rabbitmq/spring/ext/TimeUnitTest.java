package rabbitmq.spring.ext;

import java.util.concurrent.TimeUnit;

/**
 * fuquanemail@gmail.com 2016/1/22 15:17
 * description:
 * 1.0.0
 */
public class TimeUnitTest {
    public static void main(String[] args) {
        System.err.println(TimeUnit.MINUTES.toSeconds(1));

        System.err.println(TimeUnit.MINUTES);

        System.err.println(TimeUnit.MINUTES.convert(1, TimeUnit.HOURS));
    }
}
