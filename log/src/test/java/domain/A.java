package domain;

import java.io.Serializable;

/**
 * fuquanemail@gmail.com 2016/1/25 10:15
 * description:
 * 1.0.0
 */
public class A implements Serializable, S {
    private static final long serialVersionUID = -4382570203525946913L;

    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public void sayA(){
        throw new BusiException("exception ...");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("A{");
        sb.append("a='").append(a).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
