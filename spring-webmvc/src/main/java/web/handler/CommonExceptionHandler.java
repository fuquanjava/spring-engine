package web.handler;

import common.BusinessException;
import common.SystemException;
import domain.Result;
import domain.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * fuquanemail@gmail.com 2016/1/25 10:46
 * description:
 * 1.0.0
 */
public class CommonExceptionHandler implements HandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Result result = null;

        if(ex instanceof BusinessException){
            logger.error("BusinessException 异常");

            BusinessException e = (BusinessException) ex;
            result = new Result(ResultCode.COMMON_BUSINESS_EXCEPTION, false);
            result.setDescription(e.getMessage());
            result.setResultMap(e.getResultMap());
        }else if (ex instanceof SystemException){
            logger.error("SystemException 异常");

            String message = getMessage(ex);
            SystemException sex = new SystemException(message, ex);
            // 系统异常
            result = new Result(ResultCode.COMMON_SYSTEM_EXCEPTION, false);
            result.setDescription(sex.getMessage());
        }else {
            // 系统错误
            logger.error("CommonExceptionHandler catche the System Error, ", ex);
            result = new Result(ResultCode.COMMON_SYSTEM_ERROR, false);
            result.setDescription(ex.getMessage());
        }
        //判断是非需要格式化数据(is ajax and request string = json)

        return new ModelAndView();
    }

    private String getMessage(Exception ex) {
        String message = ex.getMessage();
        int index1 = message.indexOf("\n");

        if (index1 != -1) {
            message = message.substring(0, index1);
        }

        int index2 = message.indexOf("\r");

        if (index2 != -1) {
            message = message.substring(0, index2);
        }

        int index3 = message.indexOf(":");

        if (index3 != -1) {
            message = message.substring(index3 + 1).trim();
        }

        return message;
    }

}
