package com.bookstore.controller;

import com.bookstore.dto.AuthorBookDto;
import com.bookstore.entity.Author;
import com.bookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/all")
    public ResponseEntity<List<Author>> getAllAuthors(@RequestParam(name = "sortBy", required = false) String sortBy) {
        List<Author> authors = authorService.getAllAuthors(sortBy);
        return ResponseEntity.ok(authors);
    }

    @PostMapping("/addAuthorAndBook")
    public ResponseEntity<String> addAuthorAndBook(@RequestBody AuthorBookDto authorBookDto) {
        try {
            authorService.addAuthorAndBook(authorBookDto);
            return ResponseEntity.ok("Author and Book added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage() != null ? e.getMessage() : "Error adding Author and Book");
        }
    }
}
