package spring.engine.nosql.redis;

import nosql.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * fuquanemail@gmail.com 2016/9/5 14:50
 * description:
 */
public class ListApiTest extends BaseTest {

    @Autowired
    ListApi listApi;


    @Test
    public void testBrpop() throws Exception {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    listApi.brpop("c1");
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    listApi.brpop("c2");
                }
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                while (true) {
                    listApi.brpop("c3");
                }
            }
        }.start();


        Thread.currentThread().join();

    }

    @Test
    public void testLpush() throws Exception {
        new Thread() {
            @Override
            public void run() {
                int i = 1;
                while (true) {
                    listApi.lpush(String.valueOf(i++));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Thread.currentThread().join();
    }
}