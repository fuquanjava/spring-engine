package webmvc.controller;

import com.alibaba.fastjson.JSON;
import domain.R;
import domain.ResultCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * spring-engine 2015/10/31 0:06
 * fuquanemail@gmail.com
 */
@Controller
@RequestMapping(value = "/param")
public class ParamContorller  {

    @RequestMapping(value = "/btn1")
    public R btn1(HttpServletRequest request){
        String json = request.getParameter("json");
        System.err.println(json);
        List<A> list = JSON.parseArray(json,A.class);
        System.err.println(list);
        R r = new R(ResultCode.COMMON_SUCCESS, true);
        return r;
    }
    static class A{
        private int id;

        private List<String> ids;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getIds() {
            return ids;
        }

        public void setIds(List<String> ids) {
            this.ids = ids;
        }

        @Override
        public String toString() {
            return "A{" +
                    "id=" + id +
                    ", ids=" + ids +
                    '}';
        }
    }
}
