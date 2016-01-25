package com.info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * fuquanemail@gmail.com 2015/11/5 16:40
 * description:
 * 1.0.0
 */
public class log {
    private static Logger log = LoggerFactory.getLogger(log.class);

    public static void main(String[] args) throws InterruptedException {

        log.trace("======trace");
        log.debug("======debug");
        log.info("======info");
        log.warn("======warn");
        log.error("===[{}]===","error");

    }

}
