
spring-config.xml 配置内容说明:


BeanNameUrlHandlerMapping：
        表示将请求的 URL 和 Bean 名字映射，如 URL 为 “上下文/hello” ，则 Spring 配置文件
必须有一个名字为“/hello”的 Bean，上下文默认忽略 , beanName 必须以 / 开头.
    <bean name="/hello" class="webapp.HelloController"/>




SimpleControllerHandlerAdapter：
        表示所有实现了 org.springframework.web.servlet.mvc.Controller 接口的 Bean 可以作为
Spring Web MVC 中的处理器。如果需要其他类型的处理器可以通过实现 HadlerAdapter 来解决。


InternalResourceViewResolver：用于支持 Servlet、JSP 视图解析；
    viewClass：
            JstlView 表示 JSP 模板页面需要使用 JSTL 标签库，classpath 中必须包含 jstl 的相关 jar 包；

    prefix 和 suffix：
            查找视图页面的前缀和后缀（前缀[逻辑视图名]后缀） ，比如传进来的逻辑视图名为 hello，则该该

    jsp
            视图页面应该存放在“WEB-INF/jsp/hello.jsp” ；


controller说明

org.springframework.web.servlet.mvc.Controller：
        页面控制器/处理器必须实现 Controller 接口，注意别 选错了；后边我们会学习其他的处理器实现方式；

public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) ：
        功能处 理方法，实现相应的功能处理，比如收集参数、验证参数、绑定参数到命令对象、将命令对象传入业务对象进行业务处理、最后返回 ModelAndView 对象；

ModelAndView：
        包含了视图要实现的模型数据和逻辑视图名；“mv.addObject("message", "Hello World!");
        ”表示添加模型数据，此处可以是任意 POJO 对象； “mv.setViewName("hello");”表示设置逻辑视图名为“hello”，

视图解析器会将其解析为具体的视图，如前边的视图解析器 InternalResourceVi。wResolver 会将其解析为“WEB-INF/jsp/hello.jsp”。



interceptor
        我们的拦截器是单例， 因此不管用户请求多少次都只有一个拦截器实现， 即线程不安全， 那我们应该怎么记录时间呢？
解决方案是使用 ThreadLocal，它是线程绑定的变量，提供线程局部变量（一个线程一个 ThreadLocal，A 线程的
ThreadLocal 只能看到 A 线程的 ThreadLocal，不能看到 B 线程的 ThreadLocal） , 记录时间的拦截器放在拦截器链的第一个，这样得到的时间才是比较准确的


controller的演变过程

一、Spring2.5  之 前，我们现 都是通过实现 Controller  接口或其实现来定义 我们的处理器 类。 已经
@Deprecated 。

二、Spring2.5  引 入 注 解 式 处理器 支持 ， 通过@Controller  和 @RequestMapping  注 解 定义 我们的处理器
类, 并且提供 了一组 强大 的 注.
 @Controller : DefaultAnnotationHandlerMapping
 @RequestMapping : AnnotationMethodHandlerAdapter

三 、Spring3.1  使用新的 的 HandlerMapping  和 HandlerAdapter  来支持@Contoller  和@RequestMapping
注 解处理器  , 新的@Contoller 和@RequestMapping 注解支持类：
处理器映射 RequestMappingHandlerMapping
处理器适配器 RequestMappingHandlerAdapter 组合来代替 Spring2.5 开始的处理器映射 DefaultAnnotationHandlerMapping 和处理
器适配器 AnnotationMethodHandlerAdapter，提供更多的扩展点。













