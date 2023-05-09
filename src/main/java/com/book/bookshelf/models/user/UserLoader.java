package com.book.bookshelf.models.user;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class UserLoader implements CommandLineRunner {
    private final EntityManager entityManager;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        String encryptedPassword =  bCryptPasswordEncoder.encode("password");
        User user = new User("username", "", "", "defaultuser@bookshelf.com", encryptedPassword,
                "260960000001",
                Role.USER);
        user.setDateJoined(LocalDate.now());
        user.defaultActivation();
        entityManager.persist(user);
    }
}
