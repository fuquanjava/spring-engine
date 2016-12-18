package spring.rpc.domain;

import java.io.Serializable;

/**
 * fuquanemail@gmail.com 2016/5/12 10:46
 * description:
 * 1.0.0
 */
public class RpcResponse implements Serializable {

    private static final long serialVersionUID = -5490488892909222411L;

    private String requestId;
    private Throwable error;
    private Object result;


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RpcResponse{");
        sb.append("requestId='").append(requestId).append('\'');
        sb.append(", error=").append(error);
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}

