package com.enosh.userlogin.exceptions;

public class MissingAttributeException extends Exception {

    public MissingAttributeException() {
    }

    public MissingAttributeException(String message) {
        super(message);
    }

    public MissingAttributeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingAttributeException(Throwable cause) {
        super(cause);
    }

    public MissingAttributeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
