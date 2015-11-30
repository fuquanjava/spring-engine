package webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;

/**
 * Spring-demo 2015/7/10 17:25
 * fuquanemail@gmail.com
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new MyBinder());
    }
    @RequestMapping(value = "/init")
    public ModelAndView index(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        System.out.println(request.getParameterValues("f"));
        return mv;
    }

    static class MyBinder extends PropertyEditorSupport {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            if (text == null || text.equals("")) {
                text = "0";
            }
            setValue(Long.parseLong(text));
        }

        @Override
        public String getAsText() {
            return getValue().toString();
        }

        @Override
        public Object getValue() {
            return super.getValue();
        }
    }

}
