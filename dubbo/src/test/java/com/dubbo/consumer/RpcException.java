package com.dubbo.consumer;

/**
 * fuquanemail@gmail.com 2016/6/14 18:47
 * description:
 * 1.0.0
 */
public class RpcException extends RuntimeException {
    private String code;

    private String msg;

    public RpcException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RpcException(String message, String code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public RpcException(String message, Throwable cause, String code, String msg) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
    }

    public RpcException(Throwable cause, String code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public RpcException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
