package com.sit.cloudnative.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getNotesByUserId(Long userId) {
        return noteRepository.findByUserId(userId);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteByNoteId(Long noteId) {
        Note note = null;
        try {
            note = noteRepository.getOne(noteId);
        }catch(EntityNotFoundException e) {
            e.printStackTrace();
        }
        return note;
    }

    public Note createNewNote(Note note) {
        Note savingNote = new Note();
        savingNote.setUserId(note.getUserId());
        savingNote.setVideoId(note.getVideoId());
        savingNote.setContent(note.getContent());
        return noteRepository.save(savingNote);
    }

    public Note editNote(Long noteId, String content) {
        Note savingNote = noteRepository.getOne(noteId);
        savingNote.setContent(content);
        return noteRepository.save(savingNote);
    }
}
