package com.tbschedule.domain;

import java.io.Serializable;

/**
 * tbshedule-demo 2015/10/3 14:44
 * fuquanemail@gmail.com
 */
public class Order  implements Serializable{

    private long id;

    private String orderNo;

    private String gdsName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getGdsName() {
        return gdsName;
    }

    public void setGdsName(String gdsName) {
        this.gdsName = gdsName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", gdsName='" + gdsName + '\'' +
                '}';
    }
}
