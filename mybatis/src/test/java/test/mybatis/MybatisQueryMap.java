package test.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

/**
 * spring-demo 2015/6/6 21:34
 * fuquanemail@gmail.com
 */
public class MybatisQueryMap {

    @Test
    public void findDept() throws IOException {
        String conf = "SqlMapConfig.xml";
        Reader reader =
                Resources.getResourceAsReader(conf);
        //创建SessionFactory对象
        SqlSessionFactoryBuilder sfb =
                new SqlSessionFactoryBuilder();
        SqlSessionFactory sf = sfb.build(reader);
        //创建Session
        SqlSession session = sf.openSession();
        Map map = (Map) session.selectOne("findDept", 10);
        System.out.println(map.get("DEPTNO")
                + " " + map.get("DNAME"));
        session.close();
    }

    @Test
    public void findDepts() throws IOException{
        String conf = "SqlMapConfig.xml";
        Reader reader =
                Resources.getResourceAsReader(conf);
        //创建SessionFactory对象
        SqlSessionFactoryBuilder sfb =
                new SqlSessionFactoryBuilder();
        SqlSessionFactory sf = sfb.build(reader);
        //创建Session
        SqlSession session = sf.openSession();
        List<Map> list = session.selectList("findDepts");
        for(Map map : list){
            System.out.println(map.get("DEPTNO")
                    +" "+map.get("DNAME"));
        }
        session.close();
    }
}
