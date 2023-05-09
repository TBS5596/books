package com.book.bookshelf.models.message;

import com.book.bookshelf.models.forum.Forum;
import com.book.bookshelf.models.user.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessage(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    public List<Message> getMessagesByUser(User user) {
        return messageRepository.findByUser(user);
    }

    public List<Message> getMessagesForForum(Forum forum) {
        return messageRepository.findByForum(forum);
    }

    public List<Message> getMessagesByUserAndForum(User user, Forum forum) {
        return messageRepository.findByUserAndForum(user, forum);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
