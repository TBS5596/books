package com.book.bookshelf.models.message;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessage(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    public List<Message> getMessagesByUser(Long id) {
        return messageRepository.findByUser(id);
    }

    public List<Message> getMessagesForForum(Long id) {
        return messageRepository.findByForum(id);
    }

    public List<Message> getMessagesByUserAndForum(Long user_id, Long forum_id) {
        return messageRepository.findByUserAndForum(user_id, forum_id);
    }

    public void deleteMessages(Long id) {
        messageRepository.deleteById(id);
    }
}
