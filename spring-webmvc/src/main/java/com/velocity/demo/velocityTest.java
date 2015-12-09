package com.velocity.demo;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

/**
 * fuquanemail@gmail.com 2015/12/8 16:28
 * description:
 * 1.0.0
 */
public class velocityTest {
    public static void main(String[] args) {
        // 创建引擎
        VelocityEngine ve = new VelocityEngine();
        //设置模板加载路径，这里设置的是class下
        ve.setProperty(Velocity.RESOURCE_LOADER, "class");
        ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        try {
            //进行初始化操作  //velocity.properties directive.properties
            ve.init();
            //加载模板，设定模板编码
            Template layout = ve.getTemplate("velocity/layout.vm", "UTF-8");


            Template template = ve.getTemplate("velocity/index.vm", "UTF-8");
            //设置初始化数据
            VelocityContext context = new VelocityContext();
            context.put("name", "张三");
            context.put("project", "Jakarta");
            //设置输出
            StringWriter writer = new StringWriter();
            //将环境数据转化输出
            template.merge(context, writer);

            //简化操作
            layout.merge(context, writer);//mergeTemplate("test/velocity/simple1.vm", "gbk", context, writer );




            System.out.println(writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
