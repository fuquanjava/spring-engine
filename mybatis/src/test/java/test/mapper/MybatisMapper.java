package test.mapper;

import domain.Dept;
import mapper.DeptMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * spring-demo 2015/6/6 21:41
 * fuquanemail@gmail.com
 */
public class MybatisMapper {
    @Test
    public void testFindAll() throws IOException {
        String conf = "SqlMapConfig.xml";
        Reader reader =
                Resources.getResourceAsReader(conf);
        //创建SessionFactory对象
        SqlSessionFactoryBuilder sfb =
                new SqlSessionFactoryBuilder();
        SqlSessionFactory sf = sfb.build(reader);
        //创建Session
        SqlSession session = sf.openSession();
        DeptMapper mapper =
                session.getMapper(DeptMapper.class);
        //调用findAll方法
        List<Dept> list = mapper.findAll();
        for (Dept dept : list) {
            System.out.println(dept.getDeptno() + " "
                    + dept.getDname() + " "
                    + dept.getLoc());
        }
        session.close();
    }
}
