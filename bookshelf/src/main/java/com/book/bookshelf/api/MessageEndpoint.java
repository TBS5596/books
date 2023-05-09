package com.book.bookshelf.api;

import com.book.bookshelf.models.forum.Forum;
import com.book.bookshelf.models.message.Message;
import com.book.bookshelf.models.message.MessageService;
import com.book.bookshelf.models.user.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/message")
public class MessageEndpoint {
    @Autowired
    private MessageService messageService;

    // add a new message
    @PostMapping("/add")
    public ResponseEntity<Message> addAMessage(@RequestBody Message message) {
        Message newMessage = messageService.addMessage(message);
        return new ResponseEntity<>(newMessage, HttpStatus.CREATED);
    }

    // get all messages
    @GetMapping("/all")
    public ResponseEntity<List<Message>> getAllMessages () {
        List<Message> messages = messageService.getAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // get a message
    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable Long id) {
        Message message = messageService.getMessage(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // get all messages by user
    @GetMapping("/user/{user}")
    public ResponseEntity<List<Message>> getAllMessagesByUser(@RequestBody User user) {
        List<Message> messages = messageService.getMessagesByUser(user);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // get all messages by forum
    @GetMapping("/forum/{forum}")
    public ResponseEntity<List<Message>> getAllMessagesByForum(@RequestBody Forum forum) {
        List<Message> messages = messageService.getMessagesForForum(forum);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // get all messages by user and forum
    @GetMapping("/forumuser/{forum}/{user}")
    public ResponseEntity<List<Message>> getAllUserForumMessages(@RequestBody Forum forum,
                                                                 @RequestBody User user) {
        List<Message> messages = messageService.getMessagesByUserAndForum(user, forum);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // delete a message
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
