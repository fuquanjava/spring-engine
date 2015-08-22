package test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import domain.Dept;

/**
 * spring-demo 2015/6/6 18:59
 * fuquanemail@gmail.com
 */
public class MybatisPage {
    @Test
    public void testFindPage() throws IOException {
        String conf = "SqlMapConfig.xml";
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
        session.close();
    }
}