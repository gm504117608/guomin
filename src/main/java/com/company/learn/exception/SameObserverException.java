package com.company.learn.exception;

/**
 * 存在相同的元素抛出来的异常
 */
public class SameObserverException extends RuntimeException {

    public SameObserverException() {
        super();
    }

    public SameObserverException(String message) {
        super(message);
    }

    public SameObserverException(String message, Throwable cause) {
        super(message, cause);
    }

    public SameObserverException(Throwable cause) {
        super(cause);
    }

    protected SameObserverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
