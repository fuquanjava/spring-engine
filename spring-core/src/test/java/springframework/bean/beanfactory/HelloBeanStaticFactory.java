package springframework.bean.beanfactory;


import springframework.bean.HelloBean;

/**
 * @author: fuquanemail@gmail.com 2015/10/23 10:34
 * description: hello bean static factory
 * @version: 1.0.0
 */
public class HelloBeanStaticFactory {

    public static HelloBean newInstance(String name){
        return new HelloBean(name);
    }
}
