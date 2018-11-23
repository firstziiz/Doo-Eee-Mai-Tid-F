package com.sit.cloudnative.NoteService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(String exceptionMessage) {
        super("Note with id-"+ exceptionMessage+ " doesn't exist");
    }
}
