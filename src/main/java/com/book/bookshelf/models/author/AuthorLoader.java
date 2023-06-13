package com.book.bookshelf.models.author;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
@Profile("dev")
public class AuthorLoader implements CommandLineRunner {
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Author defaultAuthor = new Author();
        defaultAuthor.setName("UNKNOWN");
        entityManager.persist(defaultAuthor);
    }
}