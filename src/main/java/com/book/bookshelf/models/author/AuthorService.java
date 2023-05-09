package com.book.bookshelf.models.author;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors () {
        return authorRepository.findAll();
    }
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }
    public List<Author> getAuthorsByName(String name) {
        return authorRepository.findByNameContaining(name);
    }
}
