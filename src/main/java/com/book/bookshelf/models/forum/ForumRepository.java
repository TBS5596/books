package com.book.bookshelf.models.forum;

import com.book.bookshelf.models.book.Book;
import com.book.bookshelf.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ForumRepository  extends JpaRepository<Forum, Long> {
    List<Forum> findByBook(Book book);
    List<Forum> findByUser(User user);
}
