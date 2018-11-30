package com.sit.cloudnative.MaterialService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class MinioErrorException extends HttpErrorException {
    public MinioErrorException() {
    }

    public MinioErrorException(String message) {
        super(message);
    }

    public MinioErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinioErrorException(Throwable cause) {
        super(cause);
    }

    public MinioErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
