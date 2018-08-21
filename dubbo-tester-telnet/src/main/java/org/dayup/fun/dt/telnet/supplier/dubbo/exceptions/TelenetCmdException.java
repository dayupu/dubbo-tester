package org.dayup.fun.dt.telnet.supplier.dubbo.exceptions;

public class TelenetCmdException extends TelnetException {
    public TelenetCmdException() {
    }

    public TelenetCmdException(String message) {
        super(message);
    }

    public TelenetCmdException(String message, Throwable cause) {
        super(message, cause);
    }
}
