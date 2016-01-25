package clazz;

import domain.A;
import domain.B;
import domain.BusiException;
import domain.S;
import org.junit.Assert;
import org.junit.Test;

/**
 * fuquanemail@gmail.com 2016/1/25 10:14
 * description:
 * 1.0.0
 */
public class ClazzTest {
    @Test
    public void isAssignableFrom1() {
        //判定此 Class (a) 对象所表示的类或接口与指定的 Class(b) 参数所表示的类或接口是否相同，或是否是其超类或超接口。

        S a = new B();
        B b = new B();
        boolean r = a.getClass().isAssignableFrom(b.getClass());
        Assert.assertTrue(r);

    }

    @Test
    public void isAssignableFrom2() {
        //判定此 Class (b) 对象所表示的类或接口与指定的 Class(a ) 参数所表示的类或接口是否相同，或是否是其超类或超接口。
        A a = new A();
        B b = new B();
        boolean r = b.getClass().isAssignableFrom(a.getClass());
        Assert.assertTrue(r);

    }

    //判定指定的 Object 是否与此 Class 所表示的对象赋值兼容。此方法是 Java 语言 instanceof 运算符的动态等效方法。如果指定的 Object 参数非空，且能够在不引发 ClassCastException 的情况下被强制转换成该 Class 对象所表示的引用类型，则该方法返回 true；否则返回 false。
    @Test
    public void isInstance1(){
        A a = new A();
        B b = new B();

        boolean r = a.getClass().isInstance(B.class);
        System.err.println("r:" + r); // false

        r = b.getClass().isInstance(a);
        System.err.println("r:" + r); // false

    }

    @Test
    public void isInstance2(){
        A a = new B();
        B b = new B();

        boolean r = a.getClass().isInstance(new B());
        System.err.println("r:" + r); // true
    }

    @Test
    public void instanceof1(){
        A a = new A();
        B b = new B();
        // 它左边的对象是否是它右边的类的实例
        boolean r = b instanceof A;
        System.err.println("r:" + r); // true


    }

    @Test
    public void testE1(){
        A  a = new A();
        try {
            a.sayA();
        }catch (BusiException e){
            System.err.println("异常了...");
            //e.printStackTrace();
        }
    }
    @Test
    public void testE2(){
        A  a = new A();
        try {
            a.sayA();
        }catch (Exception e){
            System.err.println("异常了...");
            //e.printStackTrace();
            if( e instanceof BusiException){
                System.err.println(" 我是 BusiException 的 实例");
            }
        }
    }

    @Test
    public void testE3(){
        A  a = new B();
        try {
            a.sayA();
        }catch (Exception e){
            System.err.println("异常了...");
            //e.printStackTrace();
            if( e instanceof BusiException){
                System.err.println(" 我是 BusiException 的 实例");
            }
        }
    }
}
