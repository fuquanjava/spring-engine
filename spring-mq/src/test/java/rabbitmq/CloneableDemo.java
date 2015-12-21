package rabbitmq;

/**
 * fuquanemail@gmail.com 2015/12/21 9:27
 * description:
 * 1.0.0
 */
public class CloneableDemo {

    static class A implements Cloneable{
        @Override
        protected A clone() {
            try {
                return (A) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    static class B {
        @Override
        protected B clone()  {
            try {
                return (B)super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        A a = new A();
        A a1 =  a.clone();

        B b = new B();
        B b1 = b.clone();

        System.err.println(a1);
        System.err.println(b1);



    }
}

