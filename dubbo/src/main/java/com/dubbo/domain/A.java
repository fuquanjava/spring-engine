package com.dubbo.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * fuquanemail@gmail.com 2016/1/22 13:02
 * description:
 * 1.0.0
 */
public class A implements Serializable {


    private static final long serialVersionUID = -7213341698793971784L;

    private String a;

    private Date date;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("A{");
        sb.append("a='").append(a).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
