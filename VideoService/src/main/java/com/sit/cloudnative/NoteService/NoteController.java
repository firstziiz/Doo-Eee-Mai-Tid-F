package com.sit.cloudnative.NoteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class NoteController {

    @Autowired
    NoteService noteService;

    Logger logger = LoggerFactory.getLogger(NoteController.class);

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/notes/user")
    public ResponseEntity<List<Note>> getAllNoteByUserId(@RequestAttribute(name = "userId") Long userId) {
        logger.info("Getting all notes of user: " + userId);
        List<Note> userNotes = noteService.getNotesByUserId(userId);
        return new ResponseEntity<List<Note>>(userNotes, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/notes/{noteId}")
    public ResponseEntity<Note> getNoteByNoteId(@PathVariable Long noteId) {
        logger.info("Getting note:"+noteId);
        Optional<Note> note = noteService.getNoteByNoteId(noteId);
        if(note.isPresent() == false) {
            logger.info("Note:"+noteId+" is not found");
            throw new NoteNotFoundException(noteId.toString());
        }
        return new ResponseEntity<Note>(note.get(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/notes/user/video/{videoId}")
    public ResponseEntity<Note> getNoteByVideoIdAndUserId(@RequestAttribute(name = "userId") Long userId,
                                                          @PathVariable(name = "videoId") Long videoId) {
        logger.info("Getting note from video: "+videoId+" of user: "+userId);
        Optional<Note> note = noteService.getNoteByVideoIdAndUserId(videoId, userId);
        if(note.isPresent() == false) {
            logger.info("Note of user: "+userId+" from video: "+videoId+" is not found");
            throw new NoteUserVideoNotFoundException(videoId.toString(), userId.toString());
        }
        return new ResponseEntity<Note>(note.get(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/notes")
    public ResponseEntity<List<Note>> getAllNotes() {
        logger.info("Getting all notes from the system");
        List<Note> notes = noteService.getAllNotes();
        return new ResponseEntity<List<Note>>(notes, HttpStatus.OK);
    }

    @CrossOrigin("*")
    @PostMapping(path = "/notes")
    public ResponseEntity<Note> addNoteToVideo(@Valid @RequestBody Note note) {
        logger.info("Creating a note");
        Note updatedNote = noteService.createNewNote(note);
        return new ResponseEntity<Note>(updatedNote, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/notes/{noteId}", consumes = "application/json")
    public ResponseEntity<Note> updateNote(@PathVariable Long noteId, @RequestBody(required = true) Map<String,Object> jsonBody) {
        String content = jsonBody.get("content").toString();
        logger.info("Updating note:"+noteId);
        Note updatedNote = noteService.editNote(noteId, content);
        return new ResponseEntity<Note>(updatedNote, HttpStatus.OK);
    }

}
