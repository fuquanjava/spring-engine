package com.log.log4j;


import org.apache.log4j.Logger;

/**
 * fuquanemail@gmail.com 2016/6/16 19:59
 * description: 直接使用log4j 需要配置 log4j.properties
 */
public class Log4jLog {
    static Logger logger = Logger.getLogger("stdout");

    public static void main(String[] args) {
        logger.info("i'm log4j ");
    }
}
