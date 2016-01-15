package com.info;

/**
 * fuquanemail@gmail.com 2016/1/15 12:05
 * description:
 * 1.0.0
 */
public class TestObject {
    public static void main(String[] args) {
        A a1 = new A(0, 33);
        A a2 = new A(1, 2);
        System.err.println(a1.equals(a2));
        System.err.println(a1.hashCode());
        System.err.println(a2.hashCode());

    }

    static class A {
        private int a;
        private int b;
        public A(int a, int b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            A a1 = (A) o;
            if (a != a1.a) return false;
            return b == a1.b;

        }
        @Override
        public int hashCode() {
            int result = a;
            result = 31 * result + b;
            return result;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }
    }
}
