package com.dubbo.domain;

import java.util.Date;

/**
 * fuquanemail@gmail.com 2016/1/22 13:02
 * description:
 * 1.0.0
 */
public class B extends A {
    private static final long serialVersionUID = 1970253400214644218L;

    private String a;

    private Date date;


    public String getA() {
        return this.a;
    }


    public void setA(String a) {
        this.a = a;
    }


    public Date getDate() {
        return this.date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public String toString() {
        final StringBuilder sb = new StringBuilder("B{");
        sb.append("a='").append(a).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
