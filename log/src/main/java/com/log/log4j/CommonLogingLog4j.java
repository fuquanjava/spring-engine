package com.log.log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * fuquanemail@gmail.com 2016/6/16 20:22
 * <p>
 * common-logging + log4j
 * <p>
 * 必须需要 log4j.properties
 */
public class CommonLogingLog4j {
    static Log log = LogFactory.getLog(CommonLogingLog4j.class);

    public static void main(String[] args) {
        log.info("common-loggin and log4j!");
    }


}
