package com.log.jdklog;

import java.util.logging.Logger;

/**
 * fuquanemail@gmail.com 2016/6/16 19:59
 * description: jdk log 都不用依赖
 * 1.0.0
 */
public class JDKLog {
    static Logger logger = Logger.getLogger("a");

    public static void main(String[] args) {
        logger.info("i'm jdk log");
    }
}
