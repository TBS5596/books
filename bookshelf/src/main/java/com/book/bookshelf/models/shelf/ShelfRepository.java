package com.book.bookshelf.models.shelf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ShelfRepository extends JpaRepository<Shelf, Long> {
    List<Shelf> findByUser(Long userId);
    List<Shelf> findByBook(Long bookId);
}
