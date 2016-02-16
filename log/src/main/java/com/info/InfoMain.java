package com.info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * fuquanemail@gmail.com 2016/2/2 11:03
 * description:
 * 1.0.0
 */
public class InfoMain {
   static final Logger LOG = LoggerFactory.getLogger(InfoMain.class);

    public static void main(String[] args) {
        while (true){
            LOG.debug(" info main , debug ");
            LOG.info("  info main , info ");
            LOG.error(" info main , error ");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
