package com.log.log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * fuquanemail@gmail.com 2016/6/16 20:22
 * <p>
 * common-logging + log4j ， 必须需要 log4j.properties
 * <p>
 *
 * 也可以直接使用 commonlogging , 自带实现 org.apache.commons.logging.impl.LogFactoryImpl
 *
 */
public class CommonLogingLog4j {
    static Log log = LogFactory.getLog(CommonLogingLog4j.class);
    static Log log2 = LogFactory.getLog(CommonLogingLog4j.class);

    public static void main(String[] args) {
        log.info("common-loggin and log4j!");
    }
}
