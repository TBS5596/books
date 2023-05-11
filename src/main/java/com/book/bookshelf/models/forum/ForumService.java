package com.book.bookshelf.models.forum;

import com.book.bookshelf.models.book.Book;
import com.book.bookshelf.models.message.Message;
import com.book.bookshelf.models.message.MessageRepository;
import com.book.bookshelf.models.user.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ForumService {
    @Autowired
    private ForumRepository forumRepository;
    @Autowired
    private MessageRepository messageRepository;
    public Forum addForum(Forum forum) {
        return forumRepository.save(forum);
    }

    public List<Forum> getAllForums() {
        return forumRepository.findAll();
    }

    public Forum getForumById(Long id) {
        return forumRepository.findById(id).orElse(null);
    }

    public List<Forum> getForumByBook(Book book) {
        return forumRepository.findByBook(book);
    }

    public List<Forum> getForumByUser(User user) {
        return forumRepository.findByUser(user);
    }

    public void deleteForumById(Long id) {
        // delete all messages associated with the forum
        List<Message> forumMessages = messageRepository.findByForum(getForumById(id));
        if (forumMessages != null) for(Message message : forumMessages) messageRepository.deleteById(message.getId());
        forumRepository.deleteById(id);
    }
}
