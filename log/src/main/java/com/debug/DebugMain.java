package com.debug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * fuquanemail@gmail.com 2016/2/2 11:03
 * description:
 * 1.0.0
 */
public class DebugMain {
   static final Logger LOG = LoggerFactory.getLogger(DebugMain.class);

    public static void main(String[] args) {
        while (true){
            LOG.debug(" debug main , debug ");
            LOG.info("  debug main , info ");
            LOG.error(" debug main , error ");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
