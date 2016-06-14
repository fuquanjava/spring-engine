package com.dubbo.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.dubbo.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring-Engine 2015/9/20 18:30
 * fuquanemail@gmail.com
 */
public class Consumer {
    private static ClassPathXmlApplicationContext context;

    static {
        context =
                new ClassPathXmlApplicationContext("consumer/service-consumer-unit.xml");
        context.start();
    }

    public static void main(String[] args) {
        consumerBySpring();
    }

    public static void consumerBySpring() {
        HelloService helloService = (HelloService) context.getBean("helloService");

        String result = null; // bug
        try {
            System.err.println("preinvoke");
            result = helloService.testExp(true);
            System.err.println("invoked");
        } catch (Exception e) {
            e.printStackTrace();
            if(e instanceof RpcException){
                RpcException rpcException = (RpcException) e;
                System.err.println(rpcException.getCode());
                System.err.println(rpcException.getMsg());
            }else {
                System.err.println("不是 BusinessException");
            }
        }

    }

    public static void consumerByAPI() {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("helloService-consumer");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("127.0.0.1:2181");
        registry.setProtocol("zookeeper");

        // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接
        // 引用远程服务
        // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        ReferenceConfig<HelloService> reference = new ReferenceConfig<HelloService>();
        reference.setApplication(application);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference.setInterface(HelloService.class);
        reference.setVersion("1.0.0");

        // 和本地bean一样使用xxxService
        //// 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        HelloService helloService = reference.get();

        System.out.println(helloService.sayHello("dubbo"));
    }

}
