package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * fuquanemail@gmail.com 2016/3/2 17:06
 * description:
 * 1.0.0
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        System.err.println("session id:" + session.getId());
        System.err.println("session maximum time interval in seconds:" + session.getMaxInactiveInterval());

        String user = request.getParameter("user");
        String password = request.getParameter("password");

        if ("java".equals(user) && "123".equals(password)) {
            session.setAttribute("user", user);

            return "/login/success";
        }
        return "/login/fail";
    }

    @RequestMapping(value = "/main", method = {RequestMethod.POST})
    public String main(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        System.err.println("session id:" + session.getId());
        System.err.println("session maximum time interval in seconds:" + session.getMaxInactiveInterval());

        String user = (String) session.getAttribute("user");
        if(user != null){
            request.setAttribute("user", user);
            return "/login/main";
        }
        return "/login/fail";

    }

}
