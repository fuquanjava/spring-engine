package test.mybatis;

import org.junit.Test;

import java.io.IOException;

/**
 * spring-demo 2015/6/6 18:59
 * fuquanemail@gmail.com
 */
public class MybatisPage {
    @Test
    public void testFindPage() throws IOException {
       /* String conf = "SqlMapConfig.xml";
        Reader reader =
                Resources.getResourceAsReader(conf);
        //创建SessionFactory对象
        SqlSessionFactoryBuilder sfb =
                new SqlSessionFactoryBuilder();
        SqlSessionFactory sf = sfb.build(reader);
        //创建Session
        SqlSession session = sf.openSession();
        int offset = 0;//起点,从0开始
        int limit = 2;//查几条
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<Dept> list =
                session.selectList("findAll", null, rowBounds);
        for (Dept dept : list) {
            System.out.println(dept);
        }
        session.close();*/
    }
}