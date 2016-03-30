package spring.orm.xa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import spring.orm.BaseOrmTest;

/**
 * fuquanemail@gmail.com 2016/3/30 13:17
 * description:
 * 1.0.0
 */
public class XaServiceTest extends BaseOrmTest {

    @Autowired
    private XAService xaService;

    @Test
    public void findDept(){
        System.err.println(xaService.findDept(35));
    }

    @Test
    public void findUser(){
        System.err.println(xaService.findUser(1));
    }

    @Test
    public void addDept(){
        xaService.addDept();
    }

    @Test
    public void updateUser(){
        xaService.updateUser();
    }

    @Test
    public void saveOrUpdate(){
        xaService.saveOrUpdate();
    }

    @Test
    public void saveOrUpdate2(){
        xaService.saveOrUpdate2();
    }
}
