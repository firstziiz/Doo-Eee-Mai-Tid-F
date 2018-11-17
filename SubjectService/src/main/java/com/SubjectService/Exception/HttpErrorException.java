package com.SubjectService.Exception;

public abstract class HttpErrorException extends RuntimeException {
    public HttpErrorException() {
    }

    public HttpErrorException(String message) {
        super(message);
    }

    public HttpErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpErrorException(Throwable cause) {
        super(cause);
    }

    public HttpErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
