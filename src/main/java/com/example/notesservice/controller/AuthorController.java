package com.example.notesservice.controller;

import com.example.notesservice.dto.AuthorRequest;
import com.example.notesservice.model.Author;
import com.example.notesservice.repository.AuthorRepository;
import com.example.notesservice.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@Valid @RequestBody AuthorRequest request) {
        Author author = new Author(request.getName());
        Author saved = authorRepository.save(author);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + id));
        return ResponseEntity.ok(author);
    }
}
