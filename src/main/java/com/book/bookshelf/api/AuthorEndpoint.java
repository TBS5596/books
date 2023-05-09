package com.book.bookshelf.api;

import com.book.bookshelf.models.author.Author;
import com.book.bookshelf.models.author.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authors")
public class AuthorEndpoint {
    @Autowired
    private final AuthorService authorService;

    // get all the authors
    @GetMapping("/all")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    // get a specific author by id
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    // get a specific author(s) by title
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Author>> getAuthorsByName(@PathVariable String name) {
        List<Author> authors = authorService.getAuthorsByName(name);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
}
