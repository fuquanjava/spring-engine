package domain;

/**
 * fuquanemail@gmail.com 2016/1/25 10:38
 * description:
 * 1.0.0
 */
public class BusiException extends RuntimeException {

    private static final long serialVersionUID = 3541444197762187529L;

    public BusiException() {
        super();
    }

    public BusiException(String message) {
        super(message);
    }

    public BusiException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusiException(Throwable cause) {
        super(cause);
    }

    protected BusiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
