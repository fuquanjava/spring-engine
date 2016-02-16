package com.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * fuquanemail@gmail.com 2016/2/2 11:03
 * description:
 * 1.0.0
 */
public class ErrorMain {
   static final Logger LOG = LoggerFactory.getLogger(ErrorMain.class);

    public static void main(String[] args) {
        while (true){
            LOG.debug(" error main , debug ");
            LOG.info("  error main , info ");
            LOG.error(" error main , error ");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
