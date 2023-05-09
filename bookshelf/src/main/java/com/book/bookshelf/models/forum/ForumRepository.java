package com.book.bookshelf.models.forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ForumRepository  extends JpaRepository<Forum, Long> {
    List<Forum> findByUser(Long id);
    List<Forum> findByBook(Long id);
}
