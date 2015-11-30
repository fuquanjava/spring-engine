package com.dubbo.provider;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.dubbo.service.HelloService;
import com.dubbo.service.impl.HelloServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Spring-Engine 2015/9/20 18:25
 * fuquanemail@gmail.com
 */
public class Provider {
    public static void main(String[] args) throws Exception {
        dubboStartBySpring();
        //dubboStartByAPI();

    }

    public static void dubboStartBySpring() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("provider/service-provider-unit.xml");
        context.start();
        try {
            System.out.println("=======================dubbo 服务启动完毕======================");
            System.in.read(); // 按任意键退出
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dubboStartByAPI() {
        HelloService helloService = new HelloServiceImpl();
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("helloService-provider");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("127.0.0.1");
        registry.setPort(2181);
        registry.setProtocol("zookeeper");
        //registry.setUsername("aaa");
        //registry.setPassword("bbb");

        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20880);
        protocol.setThreads(10);

        // 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口

        // 服务提供者暴露服务配置
        ServiceConfig<HelloService> service = new ServiceConfig<>();
        // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        service.setApplication(application);
        service.setRegistry(registry); // 多个注册中心可以用setRegistries()
        service.setProtocol(protocol); // 多个协议可以用setProtocols()
        service.setInterface(HelloService.class);
        service.setRef(helloService);
        service.setVersion("1.0.0");

        // 暴露及注册服务
        service.export();
    }

}
