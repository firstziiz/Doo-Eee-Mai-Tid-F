package com.sit.cloudnative.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class NoteController {

    @Autowired
    NoteService noteService;

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/notes/user")
    public ResponseEntity<List<Note>> getAllNoteByUserId(@RequestAttribute(name = "userId") Long userId) {
        List<Note> userNotes = noteService.getNotesByUserId(userId);
        return new ResponseEntity<List<Note>>(userNotes, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/notes/{noteId}")
    public ResponseEntity<Note> getNoteByNoteId(@PathVariable Long noteId) {
        Note note = noteService.getNoteByNoteId(noteId);
        return new ResponseEntity<Note>(note, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/notes")
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.getAllNotes();
        return new ResponseEntity<List<Note>>(notes, HttpStatus.OK);
    }

    @CrossOrigin("*")
    @PostMapping(path = "/notes")
    public ResponseEntity<Note> addNoteToVideo(@Valid @RequestBody Note note) {
        Note updatedNote = noteService.createNewNote(note);
        return new ResponseEntity<Note>(updatedNote, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/notes/{noteId}", consumes = "application/json")
    public ResponseEntity<Note> updateNote(@PathVariable Long noteId, @RequestBody(required = true) Map<String,Object> jsonBody) {
        String content = jsonBody.get("content").toString();
        Note updatedNote = noteService.editNote(noteId, content);
        return new ResponseEntity<Note>(updatedNote, HttpStatus.OK);
    }

}
