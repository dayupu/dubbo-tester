package org.dayup.fun.dt.telnet.supplier.dubbo.exceptions;

/**
 * @author chengsong.lcs@alibaba-inc.com
 * @date 2018/8/21
 */
public class DuCallException extends Exception{

    public DuCallException() {
    }

    public DuCallException(String message) {
        super(message);
    }

    public DuCallException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuCallException(Throwable cause) {
        super(cause);
    }

    public DuCallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
