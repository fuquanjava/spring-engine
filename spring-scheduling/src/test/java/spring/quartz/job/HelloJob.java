package spring.quartz.job;

/**
 * spring-engine 2016/2/24 21:44
 * fuquanemail@gmail.com
 */
public class HelloJob {
    public void printMessage() {
        System.err.println("I am called by MethodInvokingJobDetailFactoryBean using SimpleTriggerFactoryBean");
    }
}
