package common;

import java.util.HashMap;
import java.util.Map;

/**
 * fuquanemail@gmail.com 2016/1/25 14:48
 * description:
 * 1.0.0
 */
public class BusinessException extends RuntimeException  {
    private static final long serialVersionUID = 6590357034205679567L;

    /**
     * 自定义属性
     */
    private Map<String, Object> resultMap = new HashMap<String, Object>();

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BusinessException{");
        sb.append("resultMap=").append(resultMap);
        sb.append('}');
        return sb.toString();
    }
}
