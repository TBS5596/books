package com.book.bookshelf.models.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE :subject MEMBER OF b.subjects")
    List<Book> findBySubjects(@Param("subject") String subject);
    List<Book> findByTitleContaining(String keyword);
    List<Book> findByAuthorsNameContaining(String keyword);
    @Query("SELECT b FROM Book b WHERE b.isbn_13 = :isbn_13")
    Optional<Book> findByIsbn_13(@Param("isbn_13") List<String> isbn_13);
    @Query("SELECT b FROM Book b WHERE b.isbn_10 = :isbn_10")
    Optional<Book> findByIsbn_10(@Param("isbn_10") List<String> isbn_10);
}