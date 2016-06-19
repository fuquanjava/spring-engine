package com.log.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * fuquanemail@gmail.com 2016/6/17 20:46
 * description:
 * 1.0.0
 */
public class Slf4jLogbackLog {
    private static Logger logger = LoggerFactory.getLogger(Slf4jLogbackLog.class);

    private static String STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";

    public static void main(String[] args) {
        logger.info("slf4j logback log");

        try {
            Enumeration<URL> paths = ClassLoader.getSystemResources(STATIC_LOGGER_BINDER_PATH);
            while (paths.hasMoreElements()) {
                URL path = (URL) paths.nextElement();
                logger.info("path={}", path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
