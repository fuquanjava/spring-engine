package spring.json;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * spring-engine 2015/10/30 22:49
 * fuquanemail@gmail.com
 */
public class JSONTest {
    public static void main(String[] args) {
        t1();
    }

    public static void t1(){
        List<A> list = new ArrayList<>();
        A a1 = new A();
        a1.setId(1);
        List<String> ids1 = new ArrayList<>();
        ids1.add("a");
        ids1.add("b");
        a1.setIds(ids1);

        A a2 = new A();
        a2.setId(1);
        List<String> ids2 = new ArrayList<>();
        ids2.add("c");
        ids2.add("d");
        a2.setIds(ids2);

        list.add(a1);
        list.add(a2);

        String json = JSON.toJSONString(list);
        System.err.println(json);

        List<A> list1 = JSON.parseArray(json,A.class);
        System.err.println(list1);

    }
    static class A{
        private int id;

        private List<String> ids;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getIds() {
            return ids;
        }

        public void setIds(List<String> ids) {
            this.ids = ids;
        }

        @Override
        public String toString() {
            return "A{" +
                    "id=" + id +
                    ", ids=" + ids +
                    '}';
        }
    }
}
