package org.dayup.fun.dt.telnet.client.exceptions;

public class TelnetConnException extends TelnetException {

    public TelnetConnException() {
    }

    public TelnetConnException(String message) {
        super(message);
    }

    public TelnetConnException(String message, Throwable cause) {
        super(message, cause);
    }
}
