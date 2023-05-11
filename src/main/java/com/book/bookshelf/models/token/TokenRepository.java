package com.book.bookshelf.models.token;

import com.book.bookshelf.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
    List<Token> findByUser(User user);
    @Transactional
    @Modifying
    @Query("UPDATE Token t SET t.confirmedAt = :confirmedAt WHERE t.token = :token")
    int confirmedAt(@Param("token") String token,  @Param("confirmedAt") LocalDateTime confirmedAt);
}
