package log.junit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * fuquanemail@gmail.com 2016/8/29 16:11
 * description:
 */
public class TestRunner {

    public static void main(String[] args) {

        Result result = JUnitCore.runClasses(TestJunit1.class);
        for (Failure failure : result.getFailures()) {
            System.err.println(failure.getMessage());
        }
        System.out.println(result.wasSuccessful());


    }
}
