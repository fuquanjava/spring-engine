package web.handler;

import domain.Result;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * fuquanemail@gmail.com 2016/1/25 15:07
 * description:
 * 1.0.0
 */
public class CommonMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return Result.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        // 序列化成json. write出去.
        // 判断是否是ajax / format = json 来修改 contentType.


        // 表明该请求已经处理，后面spring不会再处理
        mavContainer.setRequestHandled(true);
    }
}
