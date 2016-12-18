package spring.rpc.service.impl;


import spring.rpc.server.RpcServiceAnnotation;
import spring.rpc.service.HelloService;

/**
 * fuquanemail@gmail.com 2016/5/12 10:15
 * description:
 * 1.0.0
 */
@RpcServiceAnnotation(value = HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.err.println(" say hello invoke ");
        return "hello " + name;
    }
}
