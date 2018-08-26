package org.dayup.fish.exceptions;

public class BeatException extends RuntimeException {

    public BeatException() {
    }

    public BeatException(String message) {
        super(message);
    }

    public BeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeatException(Throwable cause) {
        super(cause);
    }
}
