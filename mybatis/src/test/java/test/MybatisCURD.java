package test;

import domain.Dept;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;


/**
 * spring-demo 2015/6/6 18:38
 * fuquanemail@gmail.com
 */
public class MybatisCURD {

    @Test
    public void testInsert() throws IOException {
        String conf = "SqlMapConfig.xml";
        Reader reader =
                Resources.getResourceAsReader(conf);
        //创建SessionFactory对象
        SqlSessionFactoryBuilder sfb =
                new SqlSessionFactoryBuilder();
        SqlSessionFactory sf = sfb.build(reader);
        //创建Session
        SqlSession session = sf.openSession();
        //调用addDept操作
        Dept dept = new Dept();
        dept.setDeptno(70);
        dept.setDname("testing");
        dept.setLoc("beijing");
        session.insert("addDept", dept);
        session.commit();
        //关闭
        session.close();
    }

    @Test
    public void testUpdate() throws IOException{
        String conf = "SqlMapConfig.xml";
        Reader reader =
                Resources.getResourceAsReader(conf);
        //创建SessionFactory对象
        SqlSessionFactoryBuilder sfb =
                new SqlSessionFactoryBuilder();
        SqlSessionFactory sf = sfb.build(reader);
        //创建Session
        SqlSession session = sf.openSession();
        //调用findById操作
        Dept dept =
                session.selectOne("findById",60);
        dept.setDname("开发部");
        dept.setLoc("北京");
        //调用updateDept操作
        session.update("updateDept",dept);
        session.commit();
        //关闭
        session.close();
    }

    @Test
    public void testFindById() throws IOException{
        String conf = "SqlMapConfig.xml";
        Reader reader =
                Resources.getResourceAsReader(conf);
        //创建SessionFactory对象
        SqlSessionFactoryBuilder sfb =
                new SqlSessionFactoryBuilder();
        SqlSessionFactory sf = sfb.build(reader);
        //创建Session
        SqlSession session = sf.openSession();
        //调用findById方法
        Dept dept =  session.selectOne("findById",10);
        System.out.println(dept);
        //关闭
        session.close();
    }

    @Test
    public void testDelete() throws IOException{
        String conf = "SqlMapConfig.xml";
        Reader reader =
                Resources.getResourceAsReader(conf);
        //创建SessionFactory对象
        SqlSessionFactoryBuilder sfb =
                new SqlSessionFactoryBuilder();
        SqlSessionFactory sf = sfb.build(reader);
        //创建Session
        SqlSession session = sf.openSession();
        //调用deleteById操作
        session.delete("deleteById",60);
        session.commit();
        //关闭
        session.close();
    }

    @Test
    public void testFindAll() throws IOException {
        String conf = "SqlMapConfig.xml";
        Reader reader = Resources.getResourceAsReader(conf);
        // 创建SessionFactory对象
        SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
        SqlSessionFactory sf = sfb.build(reader);
        // 创建Session
        SqlSession session = sf.openSession();
        // 调用findAll方法
        List<Dept> list = session.selectList("findAll");
        for (Dept dept : list) {
            System.out.println(dept);
        }
        session.close();
    }
}
