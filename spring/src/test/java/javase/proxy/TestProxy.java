package javase.proxy;

import javase.proxy.support.RefreshableProxy;
import javase.proxy.support.User;
import org.junit.Test;

import javase.proxy.support.UserService;
import javase.proxy.support.UserServiceImpl;

/**
 * fuquanemail@gmail.com 2016/1/27 17:31
 * description:
 * 1.0.0
 */
public class TestProxy {

    @Test
    public void testJDKProxy(){
        JDKProxyFactory jdkProxyFactory = JDKProxyFactory.JDKProxy.getInstance();
        UserService service = (UserService) jdkProxyFactory.bind(new UserServiceImpl());
        service.saveUser();

    }
    @Test
    public void testCglibProxy(){
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory();
        UserService service = (UserService)cglibProxyFactory.getInstance(new UserServiceImpl());
        service.saveUser();
    }

    @Test
    public void testCglibProxyBean(){
        User user = new User();
        user.setId(1);
        user.setName("user");
        RefreshableProxy<User> refreshableProxy = new RefreshableProxy<User>(user);
        User proxy = refreshableProxy.getInstance();
        System.err.println(proxy);

        User user2 = new User();
        user2.setName("zhangsan");
        user2.setId(2);
        refreshableProxy.refresh(user2);

        System.err.println(proxy);


    }
}
