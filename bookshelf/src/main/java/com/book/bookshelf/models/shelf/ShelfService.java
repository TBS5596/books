package com.book.bookshelf.models.shelf;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShelfService {
    @Autowired
    private ShelfRepository shelfRepository;

    public List<Shelf> getAllShelfEntries() {
        return shelfRepository.findAll();
    }

    public List<Shelf> getByBook(Long id) {
        return shelfRepository.findByBook(id);
    }

    public List<Shelf> getByUser(Long id) {
        return shelfRepository.findByUser(id);
    }

    public Shelf getById(Long id) {
        return shelfRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        shelfRepository.deleteById(id);
    }
}
