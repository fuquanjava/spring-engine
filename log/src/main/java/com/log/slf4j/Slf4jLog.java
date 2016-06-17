package com.log.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * fuquanemail@gmail.com 2016/6/17 20:37
 * description:
 * 1.0.0
 */
public class Slf4jLog {

    private static Logger logger = LoggerFactory.getLogger(Slf4jLog.class);

    public static void main(String[] args) {
        logger.info("i'm slf4j-api, ok ?");

    }
}
