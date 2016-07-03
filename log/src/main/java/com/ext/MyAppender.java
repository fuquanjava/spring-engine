/*
package ext;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

*/
/**
 * fuquanemail@gmail.com 2016/1/24 10:16
 * description:
 * 1.0.0
 *//*

public class MyAppender extends AppenderBase<ILoggingEvent> {
    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        String theadName = iLoggingEvent.getThreadName();
        System.err.println("theadName:" + theadName);

        Level level = iLoggingEvent.getLevel();
        System.err.println("level:" + level);

        String message = iLoggingEvent.getMessage();
        System.err.println("message:" + message);

        String formattedMesg =  iLoggingEvent.getFormattedMessage();
        System.err.println("formattedMesg:" + formattedMesg);

        System.err.println("ILoggingEvent: " +iLoggingEvent);
    }
}
*/
