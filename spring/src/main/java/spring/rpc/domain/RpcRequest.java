package spring.rpc.domain;

import java.io.Serializable;
import java.util.Arrays;

/**
 * fuquanemail@gmail.com 2016/5/12 10:44
 * description:
 * 1.0.0
 */
public class RpcRequest implements Serializable {


    private static final long serialVersionUID = -8838143537650049346L;

    private String requestId;
    private String className;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("RpcRequest{");
        sb.append("requestId='").append(requestId).append('\'');
        sb.append(", className='").append(className).append('\'');
        sb.append(", methodName='").append(methodName).append('\'');
        sb.append(", parameterTypes=").append(Arrays.toString(parameterTypes));
        sb.append(", parameters=").append(Arrays.toString(parameters));
        sb.append('}');
        return sb.toString();
    }
}
