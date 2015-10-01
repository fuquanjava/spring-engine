package handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import domain.Result;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by cdtx on 2015/9/28.
 */
public class CommonMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return Result.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
            throws Exception {
        if (returnValue != null) {
            Result result = (Result) returnValue;
            HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
            //response.setCharacterEncoding("UTF-8");

            if (isAjaxUrl(webRequest)) {
                response.setContentType("application/json;charset=UTF-8");
            } else {
                response.setContentType("text/html;charset=UTF-8");
            }

            StringBuffer responseSb = new StringBuffer();

            if (isJsonp(webRequest)) {
                String callback = webRequest.getParameter("callback");
                responseSb.append("(").append(callback).append(toJSONString(result)).append(")");
            } else {
                responseSb.append(toJSONString(result));
            }

            response.getWriter().println(responseSb.toString());

            // 表明该请求已经处理，后面spring不会再处理
            mavContainer.setRequestHandled(true);
        }
    }

    private boolean isAjaxUrl(NativeWebRequest webRequest) {
        String format = webRequest.getParameter("format");
        if ("json".equals(format) || "jsonp".equals(format)) {
            return true;
        }

        String accept = webRequest.getHeader("Accept");
        if (accept.contains("application/json") || accept.contains("application/jsonp")) {
            return true;
        }

        return false;
    }

    private boolean isJsonp(NativeWebRequest webRequest) {
        String format = webRequest.getParameter("format");
        if ("jsonp".equals(format)) {
            return true;
        }

        String accept = webRequest.getHeader("Accept");
        if (accept.contains("application/jsonp")) {
            return true;
        }

        return false;
    }

    private String toJSONString(Result result) {
        if (result.isUseDateFormat()) {
            JSON.DEFFAULT_DATE_FORMAT = result.getDateFormat();
            return JSON.toJSONString(result, SerializerFeature.WriteDateUseDateFormat);
        } else {
            return JSON.toJSONString(result);
        }
    }

}
