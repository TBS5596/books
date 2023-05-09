package com.book.bookshelf.api;

import com.book.bookshelf.models.forum.Forum;
import com.book.bookshelf.models.forum.ForumRepository;
import com.book.bookshelf.models.forum.ForumService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/forum")
public class ForumEndpoint {
    @Autowired
    private ForumService forumService;

    // create a new forum
    @PostMapping("/add")
    public ResponseEntity<Forum> addForum(@RequestBody Forum forum) {
        Forum newForum = forumService.addForum(forum);
        return new ResponseEntity<>(newForum, HttpStatus.CREATED);
    }

    // get a forum
    @GetMapping("/{id}")
    public ResponseEntity<Forum> getForumById(@PathVariable Long id) {
        Forum myForum = forumService.getForumById(id);
        return new ResponseEntity<>(myForum, HttpStatus.OK);
    }

    // get all forums
    @GetMapping("/all")
    public ResponseEntity<List<Forum>> getAllForums() {
        List<Forum> forums = forumService.getAllForums();
        return new ResponseEntity<>(forums, HttpStatus.OK);
    }

    // get forums by book
    @GetMapping("/book/{id}")
    public ResponseEntity<List<Forum>> getForumByBook(@PathVariable Long id) {
        List<Forum> forums = forumService.getForumByBook(id);
        return new ResponseEntity<>(forums, HttpStatus.OK);
    }

    // get forums by user
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Forum>> getForumByUser(@PathVariable Long id) {
        List<Forum> forums = forumService.getForumByUser(id);
        return new ResponseEntity<>(forums, HttpStatus.OK);
    }

    // delete a forum
    @DeleteMapping("/{id}")
    public ResponseEntity<Forum> deleteForum(@PathVariable Long id) {
        forumService.deleteForumById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}