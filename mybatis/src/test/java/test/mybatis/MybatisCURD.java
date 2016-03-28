package test.mybatis;

import domain.UserDO;
import mapper.UserDAOMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;


/**
 * spring-demo 2015/6/6 18:38
 * fuquanemail@gmail.com
 */
public class MybatisCURD extends MybatisBaseTest {

    @Test
    public void testCache() throws InvocationTargetException, IllegalAccessException {

        SqlSession session = sqlSessionFactory.openSession();
        UserDAOMapper mapper = session.getMapper(UserDAOMapper.class);
        UserDO user = mapper.findById(1);
        System.err.println(user);
        /**
         * mapper.xml 中开启了cache 标签、第二次不会 再次执行sql,会提示:
         * DEBUG main org.apache.ibatis.cache.decorators.LoggingCache - Cache Hit Ratio [test.mybatis.mapper.ResourceMapper]: 0.0
         */
        user = mapper.findById(1);
        System.err.println(user);

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDO, user);
        userDO.setRemark("测试修改");

        /**
         * 修改
         */
        int rows = mapper.updateById(userDO);
        System.err.println(rows);
        // 提交事务
        session.commit();

        /**
         * 修改完之后，会再次执行sql查询。
         */
        user = mapper.findById(1);
        System.err.println(user);

        user = mapper.findById(1);
        System.err.println(user);

    }


   /* @Test
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
    public void testUpdate() throws IOException {
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
                session.selectOne("findById", 60);
        dept.setDname("开发部");
        dept.setLoc("北京");
        //调用updateDept操作
        session.update("updateDept", dept);
        session.commit();
        //关闭
        session.close();
    }

    @Test
    public void testFindById() throws IOException {
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
        Dept dept = session.selectOne("findById", 10);
        System.out.println(dept);
        //关闭
        session.close();
    }

    @Test
    public void testDelete() throws IOException {
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
        session.delete("deleteById", 60);
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
    }*/
}
