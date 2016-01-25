package common;

/**
 * fuquanemail@gmail.com 2016/1/25 14:49
 * description:
 * 1.0.0
 */
public class SystemException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -6971716908203238516L;

    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

}
