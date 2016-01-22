package rabbitmq.spring.bean.beanfactory;

import rabbitmq.spring.bean.HelloBean;

/**
 * @author: fuquanemail@gmail.com 2015/10/23 10:42
 * description:
 * @version: 1.0.0
 */
public class HelloBeanFactory {

    public HelloBean newInstance(String name){
        return new HelloBean(name);
    }
}
