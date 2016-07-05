package tbschedule;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * fuquanemail@gmail.com 2016/7/5 15:13
 * description:
 * 1.0.0
 */
public class TBSccheduleTest {

    @Test
    public void testTBScchedule() {
        // 初始化Spring
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-unit.xml");
        ctx.start();
        try {
            System.out.println("=======================启动完毕======================");
            System.in.read(); // 按任意键退出
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
