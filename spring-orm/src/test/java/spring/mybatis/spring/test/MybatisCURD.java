package spring.mybatis.spring.test;

import spring.mybatis.domain.User;
import spring.mybatis.mapper.UserMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * fuquanemail@gamil.com
 * Date: 14-7-16 下午10:20
 */

public class MybatisCURD {
    static Logger logger = LoggerFactory.getLogger(MybatisCURD.class);

    UserMapper userMapper ;


    @Before
    public void setUp(){
        String conf = "applicationContext-mybatis.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
        userMapper = (UserMapper) ac.getBean("userMapper");
        logger.info("userMapper = [{}]", userMapper);
    }
    @After
    public void tearDown(){

    }
    @Test
    public void add() throws IOException{
        User user = new User();
        user.setId(2);
        user.setName("jack");
        user.setEmail("jack@126.vom");
        user.setLastlogintime(new Date());
        userMapper.addUser(user);
    }
    @Test
    public void testFindAll() throws IOException {
        List<User> list =  userMapper.findAll();
        for(User user : list){
            System.out.println(user);
        }

    }
}
