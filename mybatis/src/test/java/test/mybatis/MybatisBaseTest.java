package test.mybatis;

import domain.UserDO;
import mapper.UserDAOMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;

/**
 * fuquanemail@gmail.com 2016/3/28 9:15
 * description:
 * 1.0.0
 */
public class MybatisBaseTest {
    //每一个MyBatis的应用程序的入口是SqlSessionFactoryBuilder，它的作用是通过XML配置文件创建Configuration对象（当然也可以在程序中自行创建），
    // 然后通过build方法创建SqlSessionFactory对象。没有必要每次访问Mybatis就创建一次SqlSessionFactoryBuilder，
    // 通常的做法是创建一个全局的对象就可以了
    protected static SqlSessionFactoryBuilder sqlSessionFactoryBuilder;


    //SqlSessionFactory对象由SqlSessionFactoryBuilder创建。它的主要功能是创建SqlSession对象，和SqlSessionFactoryBuilder对象一样，
    // 没有必要每次访问Mybatis就创建一次SqlSessionFactory，通常的做法是创建一个全局的对象就可以了。
    // SqlSessionFactory对象一个必要的属性是Configuration对象,它是保存Mybatis全局配置的一个配置对象，
    // 通常由SqlSessionFactoryBuilder从XML配置文件创建
    protected static SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        Reader reader =  Resources.getResourceAsReader("mybatis-config.xml");
        sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);

    }

    @Test
    public void testSqlSessionFactory(){
        Environment environment = sqlSessionFactory.getConfiguration().getEnvironment();
        System.err.println("environment.getDataSource : "+ environment.getDataSource());
    }

    /**
     * SqlSession

     SqlSession对象的主要功能是完成一次数据库的访问和结果的映射，它类似于数据库的session概念，
     由于不是线程安全的，所以SqlSession对象的作用域需限制方法内。

     SqlSession的默认实现类是DefaultSqlSession，它有两个必须配置的属性：Configuration和Executor。
     Configuration前文已经描述这里不再多说。SqlSession对数据库的操作都是通过Executor来完成的。

     SqlSession有一个重要的方法getMapper，顾名思义，这个方式是用来获取Mapper对象的。

     什么是Mapper对象？根据Mybatis的官方手册，应用程序除了要初始并启动Mybatis之外，还需要定义一些接口，
     接口里定义访问数据库的方法，存放接口的包路径下需要放置同名的XML配置文件。


     SqlSession的getMapper方法是联系应用程序和Mybatis纽带，应用程序访问getMapper时，
     Mybatis会根据传入的接口类型和对应的XML配置文件生成一个代理对象，这个代理对象就叫Mapper对象。

     应用程序获得Mapper对象后，就应该通过这个Mapper对象来访问Mybatis的SqlSession对象，这样就达到里插入到Mybatis流程的目的

     */
    @Test
    public void testEnv(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDAOMapper mapper = sqlSession.getMapper(UserDAOMapper.class);
        UserDO userDO = mapper.findById(1);
        System.err.println(userDO);

    }
    /**
     *
     * Executor

            Executor对象在创建Configuration对象的时候创建，并且缓存在Configuration对象里。Executor对象的主要功能是调用StatementHandler访问数据库，并将查询结果存入缓存中（如果配置了缓存的话）。

     StatementHandler

            StatementHandler是真正访问数据库的地方，并调用ResultSetHandler处理查询结果。

     ResultSetHandler

            处理查询结果
     */
}
