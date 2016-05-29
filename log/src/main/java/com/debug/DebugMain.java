package com.debug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * fuquanemail@gmail.com 2016/2/2 11:03
 * description:
 * 1.0.0
 */
public class DebugMain {
    static final Logger LOG = LoggerFactory.getLogger(DebugMain.class);

    public static void main(String[] args) {

        try {
            testException();
        } catch (Exception e) {
            //e.printStackTrace();
            LOG.info("exception " + e);

            LOG.info("exception ", e);

            LOG.error(e.getMessage());
        }
    }

    public static void testLogLevel() {
        String s = null;
        LOG.error("s = [{}]", s);
        LOG.error("s = " + s);

        LOG.error("order no [{}], new order no [{}]", 112, 1);
        LOG.debug(" debug main , debug ");
        LOG.info("  debug main , info ");
        LOG.error(" debug main , error ");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testException() throws Exception {

        if (2 > 1) {
            throw new RuntimeException("测试异常");
        }
    }
}
