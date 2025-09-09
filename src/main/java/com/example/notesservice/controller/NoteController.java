package com.example.notesservice.controller;

import com.example.notesservice.dto.NoteRequest;
import com.example.notesservice.exception.ResourceNotFoundException;
import com.example.notesservice.model.Author;
import com.example.notesservice.model.Note;
import com.example.notesservice.repository.AuthorRepository;
import com.example.notesservice.repository.NoteRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteRepository noteRepository;
    private final AuthorRepository authorRepository;

    public NoteController(NoteRepository noteRepository, AuthorRepository authorRepository) {
        this.noteRepository = noteRepository;
        this.authorRepository = authorRepository;
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@Valid @RequestBody NoteRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + request.getAuthorId()));
        Note note = new Note(request.getTitle(), request.getContent(), author);
        Note saved = noteRepository.save(note);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with id " + id));
        return ResponseEntity.ok(note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with id " + id));
        noteRepository.delete(note);
        return ResponseEntity.noContent().build();
    }
}
