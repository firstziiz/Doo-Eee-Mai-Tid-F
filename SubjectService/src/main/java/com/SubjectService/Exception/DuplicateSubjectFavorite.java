package com.SubjectService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateSubjectFavorite extends HttpErrorException {
    public DuplicateSubjectFavorite() {
    }

    public DuplicateSubjectFavorite(String message) {
        super(message);
    }

    public DuplicateSubjectFavorite(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateSubjectFavorite(Throwable cause) {
        super(cause);
    }

    public DuplicateSubjectFavorite(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
