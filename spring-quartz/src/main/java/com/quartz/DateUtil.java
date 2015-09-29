package com.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * fuquanemail@gmail.com
 * 2015/9/29 15:45
 */
public class DateUtil {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static String getNow(){
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
}
