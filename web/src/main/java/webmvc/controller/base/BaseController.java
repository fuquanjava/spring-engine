package webmvc.controller.base;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * spring-demo 2015/7/25 9:39
 * fuquanemail@gmail.com
 */
public class BaseController {
    protected final static String STAFF_ID = "webLogin";
    protected final static String STAFFVO = "staffVo";

    @Autowired
    protected HttpServletRequest request;
    /**
     * 从Request获取参数
     * @param name
     * @return
     */
    final protected String getParam(String name){
        return request.getParameter(name);
    }
}
