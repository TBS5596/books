package com.book.bookshelf.api;

import com.book.bookshelf.models.shelf.Shelf;
import com.book.bookshelf.models.shelf.ShelfService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/shelf")
public class ShelfEndpoint {
    @Autowired
    private ShelfService shelfService;

    // get all the shelf entries
    @GetMapping("/all")
    public ResponseEntity<List<Shelf>> getAllShelfEntries() {
        List<Shelf> shelfEntries = shelfService.getAllShelfEntries();
        return new ResponseEntity<>(shelfEntries, HttpStatus.OK);
    }

    // get a specific shelves
    @GetMapping("/id/{id}")
    public ResponseEntity<Shelf> getAShelfEntry(@PathVariable Long id) {
        Shelf shelfEntry = shelfService.getById(id);
        return new ResponseEntity<>(shelfEntry, HttpStatus.OK);
    }

    // get shelf entries by user
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Shelf>> getShelfEntriesByUser(@PathVariable Long id) {
        List<Shelf> shelfEntries = shelfService.getByUser(id);
        return new ResponseEntity<>(shelfEntries, HttpStatus.OK);
    }

    // get shelf entries by book
    @GetMapping("/book/{id}")
    public ResponseEntity<List<Shelf>> getShelfEntriesByBook(@PathVariable Long id) {
        List<Shelf> shelfEntries = shelfService.getByBook(id);
        return new ResponseEntity<>(shelfEntries, HttpStatus.OK);
    }

    // delete a specific shelf entry
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Shelf> deleteAShelfEntry(@PathVariable Long id) {
        shelfService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
