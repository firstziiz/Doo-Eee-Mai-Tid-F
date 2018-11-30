package com.sit.cloudnative.NoteService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoteUserVideoNotFoundException extends RuntimeException {
    public NoteUserVideoNotFoundException(String videoId, String userId) {
        super("Note of user: "+userId+" from video: "+videoId+" is not found");
    }
}
