package com.dubbo.service.exception;

/**
 * fuquanemail@gmail.com 2016/6/15 9:56
 * description:
 * 1.0.0
 */
public class BusinessExceptionA extends RuntimeException {
    private String code;

    private String msg;

    /**
     * 可以通过override掉异常类的fillInStackTrace()方法为空方法，使其不拷贝栈信息，
     * @return
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }

    public BusinessExceptionA(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessExceptionA(String message, String code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public BusinessExceptionA(String message, Throwable cause, String code, String msg) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
    }

    public BusinessExceptionA(Throwable cause, String code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public BusinessExceptionA(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.msg = msg;
    }
}
