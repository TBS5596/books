package com.book.bookshelf.models.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MessageRepository extends JpaRepository <Message, Long> {
    List<Message> findByUser(Long id);
    List<Message> findByForum(Long id);
    @Query("SELECT m FROM Message m WHERE m.user_id = :user_id AND m.forum_id = :forum_id")
    List<Message> findByUserAndForum(@Param("user_id") Long user_id, @Param("forum_id") Long forum_id);
}
