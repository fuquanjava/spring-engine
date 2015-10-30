package webmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * spring-demo 2015/6/22 14:19
 * fuquanemail@gmail.com
 */
@Controller // 或 @RequestMapping  //将一个POJO类声明为处理器
public class HelloController2 {
    static Logger logger = LoggerFactory.getLogger(HelloController2.class);

    @RequestMapping(value = "/hello")
    public ModelAndView hello (HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info(" HelloController ... handleRequest ");
        //1、收集参数、验证参数
        //2、绑定参数到命令对象
        //3、将命令对象传入业务对象进行业务处理
        //4、选择下一个页面
        ModelAndView mv = new ModelAndView();
        //添加模型数据 可以是任意的POJO对象
        mv.addObject("message", "Hello World  man !");
        //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
        mv.setViewName("hello");
        Thread.sleep(1000);
        System.out.println(HelloController2.this.hashCode());
        System.out.println(Thread.currentThread().getName());

        return mv;
    }
}
