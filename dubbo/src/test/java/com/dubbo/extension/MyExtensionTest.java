package com.dubbo.extension;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * fuquanemail@gmail.com
 */
public class MyExtensionTest {

    ClassPathXmlApplicationContext context;

    // @Before
    public void setUp() throws Exception {
        context =
                new ClassPathXmlApplicationContext("provider/dubbo-provider-unit.xml");

        context.start();
    }

   // @After
    public void tearDown() throws Exception {
        context.destroy();
    }

    @Test
    public void testSayHello() throws Exception {
        ExtensionLoader extensionLoader = ExtensionLoader.getExtensionLoader(MyExtension.class);

        MyExtension myFirstExtension = (MyExtension) extensionLoader.getAdaptiveExtension();
        System.out.println(myFirstExtension.sayHello("man", MyExtension.OTHER_TYPE));
    }
}