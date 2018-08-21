package org.dayup.fun.dt.telnet.supplier.dubbo.exceptions;

public class TelnetTimeoutException extends TelnetException{
    public TelnetTimeoutException() {
    }

    public TelnetTimeoutException(String message) {
        super(message);
    }

    public TelnetTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
