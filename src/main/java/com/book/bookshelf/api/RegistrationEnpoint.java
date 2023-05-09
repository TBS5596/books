package com.book.bookshelf.api;

import com.book.bookshelf.models.user.User;
import com.book.bookshelf.models.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/register")
public class RegistrationEnpoint {
    private final UserService userService;

    // create a new user
    @PostMapping("/create")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        String token = userService.addUser(user);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }
}
