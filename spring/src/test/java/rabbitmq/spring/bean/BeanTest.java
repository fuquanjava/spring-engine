package rabbitmq.spring.bean;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;

/**
 * @author: fuquanemail@gmail.com 2015/10/23 10:20
 * description:
 * @version: 1.0.0
 */
public class BeanTest {
    ClassPathXmlApplicationContext context = null;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("classpath:spring.xml");
    }

    @Test
    public void testBeanConstructor() {
        HelloBean helloBean1 = (HelloBean) context.getBean("bean1");
        HelloBean helloBean2 = (HelloBean) context.getBean("bean2");
        helloBean1.sayName();
        helloBean2.sayName();

        HelloBean helloBean3 = (HelloBean) context.getBean("bean3");
        helloBean3.sayName();

        HelloBean helloBean4 = (HelloBean) context.getBean("bean4");
        helloBean4.sayName();
    }
    @Test
    public void testXmlBeanFactoryBaseOnFileSystem() {
        //1.准备配置文件，从文件系统获取配置文件，默认是相对路径，可以指定绝对路径
        File file = new File("spring.xml");
        Resource resource = new FileSystemResource(file);
        //2.初始化容器
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        //2、从容器中获取Bean
        HelloBean HelloBean = beanFactory.getBean("bean1", HelloBean.class);
        //3、执行业务逻辑
        HelloBean.sayName();
    }

    @Test
    public void testXmlBeanFactoryBaseOnClassPath() {
        //1.准备配置文件，从当前类加载路径中获取配置文件
        Resource resource = new ClassPathResource("src/main/resource/spring.xml");
        //2.初始化容器
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        //2、从容器中获取Bean
        HelloBean HelloBean = beanFactory.getBean("bean1", HelloBean.class);
        //3、执行业务逻辑
        HelloBean.sayName();
    }

    @Test
    public void testClassPathXmlApplicationContext() {
        //1.准备配置文件，从当前类加载路径中获取配置文件
        //2.初始化容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("spring.xml");
        //2、从容器中获取Bean
        HelloBean HelloBean = beanFactory.getBean("bean1", HelloBean.class);
        //3、执行业务逻辑
        HelloBean.sayName();
    }

    @Test
    public void testFileSystemApplicationContext() {
        //1.准备配置文件，从文件系统获取配置文件，默认是相对路径，可以指定绝对路径
        //2.初始化容器
        BeanFactory beanFactory = new FileSystemXmlApplicationContext("src\\main\\resources\\spring.xml");
        //2、从容器中获取Bean
        HelloBean HelloBean = beanFactory.getBean("bean1", HelloBean.class);
        //3、执行业务逻辑
        HelloBean.sayName();
    }
    

}
