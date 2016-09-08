package com.log.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * fuquanemail@gmail.com 2016/9/7 19:50
 * description:
 */
public class EvaluatorFilterLog {

    private static Logger log = LoggerFactory.getLogger(EvaluatorFilterLog.class);


    public static void main(String[] args) throws InterruptedException {
        while (true){
            Thread.sleep(100);
            log.info("EvaluatorFilterLog = {}", System.currentTimeMillis());

            log.info("sql 不会记录到log!");

            log.info("EvaluatorFilterLog = {}", System.currentTimeMillis());

        }
    }
}
