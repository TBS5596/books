package com.book.bookshelf.models.shelf;

import com.book.bookshelf.models.book.Book;
import com.book.bookshelf.models.user.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShelfService {
    @Autowired
    private ShelfRepository shelfRepository;

    public Shelf addShelfEntry(Shelf entry) {
        return shelfRepository.save(entry);
    }

    public List<Shelf> getAllShelfEntries() {
        return shelfRepository.findAll();
    }

    public List<Shelf> getByBook(Book book) {
        return shelfRepository.findByBook(book);
    }

    public List<Shelf> getByUser(User user) {
        return shelfRepository.findByUser(user);
    }

    public Shelf getById(Long id) {
        return shelfRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        shelfRepository.deleteById(id);
    }
}
