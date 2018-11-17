package com.SubjectService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ClientErrorException extends RuntimeException{
    public ClientErrorException() {
    }

    public ClientErrorException(String message) {
        super(message);
    }

    public ClientErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientErrorException(Throwable cause) {
        super(cause);
    }

    public ClientErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
