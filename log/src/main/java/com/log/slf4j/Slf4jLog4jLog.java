package com.log.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * fuquanemail@gmail.com
 */
public class Slf4jLog4jLog {
    private static Logger logger = LoggerFactory.getLogger(Slf4jLog4jLog.class);
    public static void main(String[] args) {
        logger.info("slf4j log4j log");
    }

}
