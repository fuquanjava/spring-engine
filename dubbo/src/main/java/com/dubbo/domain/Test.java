package com.dubbo.domain;

import com.alibaba.com.caucho.hessian.io.HessianInput;
import com.alibaba.com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * fuquanemail@gmail.com 2016/1/22 13:08
 * description:
 * 1.0.0
 */
public class Test {
    public static void main(String[] args) throws IOException {
        B b = new B();
        b.setDate(new Date());
        b.setA("a");

        System.err.println(b);

        byte [] bytes = serialize(b);
        Object o = deserialize(bytes);
        System.err.println(o.toString());

    }
    public static byte[] serialize(Object obj) throws IOException {
        if(obj==null) throw new NullPointerException();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(obj);
        return os.toByteArray();
    }
    public static Object deserialize(byte[] by) throws IOException{
        if(by==null) throw new NullPointerException();
        ByteArrayInputStream is = new ByteArrayInputStream(by);
        HessianInput hi = new HessianInput(is);
        return hi.readObject();
    }
}
