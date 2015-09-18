package controller;

import controller.base.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import session.impl.SessionManager;
import util.CoreContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * spring-demo 2015/7/25 9:42
 * fuquanemail@gmail.com
 */
@Controller
@RequestMapping(value = "/user")
public class HelloController extends BaseController {

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request , HttpServletResponse response){
        System.out.println(Thread.currentThread().getName());


        /*String name= getParam("name");
        String pwd = getParam("pwd");
        if("admin".equals(name) && "123".equals(pwd)){
            //存入session
            StaffVo staffVo = new StaffVo();
            staffVo.setName(name);
            staffVo.setPwd(pwd);
            staffVo.setLogin(Boolean.TRUE);
            request.getSession().setAttribute(STAFFVO, staffVo);
            addStaffCookie(request, response , name);
            return "main";
        }*/
        return "login";
    }
    public static void addStaffCookie(HttpServletRequest request, HttpServletResponse response,String staffId){
        SessionManager sessionManager=(SessionManager)(CoreContextHolder.getContext().getBean("sessionManager"));
        sessionManager.addCookie(request, response, STAFF_ID, staffId);

    }
    @RequestMapping(value = "/tologin")
    public String toLogin(HttpServletRequest request){
        String admin = (String) request.getSession().getAttribute(STAFFVO);
        if(StringUtils.isBlank(admin)){
            return "login";
        }
        return "main";
    }
    
}
