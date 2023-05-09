package com.book.bookshelf.models.message;

import com.book.bookshelf.models.forum.Forum;
import com.book.bookshelf.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface MessageRepository extends JpaRepository <Message, Long> {
    List<Message> findByUser(User user);
    List<Message> findByForum(Forum forum);
    @Query("SELECT m FROM Message m WHERE m.user = :user AND m.forum = :forum")
    List<Message> findByUserAndForum(@Param("user") User user, @Param("forum") Forum forum);
}
