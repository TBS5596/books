package com.book.bookshelf.models.forum;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ForumService {
    @Autowired
    private ForumRepository forumRepository;

    public Forum addForum(Forum forum) {
        return forumRepository.save(forum);
    }

    public List<Forum> getAllForums() {
        return forumRepository.findAll();
    }

    public Forum getForumById(Long id) {
        return forumRepository.findById(id).orElse(null);
    }

    public List<Forum> getForumByUser(Long userId) {
        return forumRepository.findByUser(userId);
    }

    public List<Forum> getForumByBook(Long bookId) {
        return forumRepository.findByBook(bookId);
    }

    public void deleteForumById(Long id) {
        forumRepository.deleteById(id);
    }
}
