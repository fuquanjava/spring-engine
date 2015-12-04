package webmvc.controller;

import domain.R;
import domain.ResultCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * spring-engine 2015/11/30 22:30
 * fuquanemail@gmail.com
 */

@Controller
@RequestMapping("/request")
public class RequestParametersController {

    /**
     * 若方法参数名称和需要绑定的uri template中变量名称不一致，需要在@PathVariable("name")指定uri template中的名称。
     * @param id
     * @return
     */
    @RequestMapping("/uri/{id}")
    public R pathUri(@PathVariable String id) {
        System.err.println("id-->" + id);
        R r = new R(ResultCode.COMMON_SUCCESS, true);
        r.setProperty("id", id);
        return r;
    }

    /**
     * @RequestHeader 注解，可以把Request请求header部分的值绑定到方法的参数上
     * @CookieValue 可以把Request header中关于cookie的值绑定到方法的参数上。
     * @param encoding
     * @return
     */
    @RequestMapping("requestHeader")
    public R requestHeader(@RequestHeader("Accept-Encoding") String encoding){
        System.err.println("encoding-->" + encoding);
        R r = new R(ResultCode.COMMON_SUCCESS, true);
        r.setProperty("encoding", encoding);
        return r;
    }

    @RequestMapping(value = "requestBody")
    public R requestBody(@RequestBody List<User> users){
        R r = new R(ResultCode.COMMON_SUCCESS, true);
        r.setProperty("users", users);
        return r;
    }

    @RequestMapping(value = "requestBody2")
    public R requestBody2(@RequestBody List<Long> ids){
        R r = new R(ResultCode.COMMON_SUCCESS, true);
        r.setProperty("ids", ids);
        return r;
    }

    @RequestMapping(value = "requestParam")
    public R requestParam(@RequestParam("id") String id,
                          @RequestParam("requestId") Integer rid) {
        System.err.println("id:"+id);
        System.err.println("requestId:"+rid);

        R r = new R(ResultCode.COMMON_SUCCESS, true);
        r.setProperty("id", id);
        r.setProperty("requestId", rid);
        return r;
    }

    @RequestMapping(value = "requestParam2")
    public R requestParam2(User user , HttpServletRequest request) {
        System.err.println("id:"+user.id);
        String rip = request.getParameter("requestId");

        String ids = request.getParameter("arg");
        System.err.println(ids);

        System.err.println("requestId:"+rip);
        R r = new R(ResultCode.COMMON_SUCCESS, true);
        r.setProperty("id", user.id);
        r.setProperty("rip", rip);
        return r;
    }



    static class User{
        Integer id;
        String name;
        String desc;

        private List<Integer> ids;

        public List<Integer> getIds() {
            return ids;
        }

        public void setIds(List<Integer> ids) {
            this.ids = ids;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }




}

