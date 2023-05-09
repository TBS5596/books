package com.book.bookshelf.api;

import com.book.bookshelf.models.book.Book;
import com.book.bookshelf.models.shelf.Shelf;
import com.book.bookshelf.models.shelf.ShelfService;
import com.book.bookshelf.models.user.User;
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

    // add a shelf entry
    @PostMapping("/add")
    public ResponseEntity<Shelf> addAShelfEntry(@RequestBody Shelf entry) {
        Shelf newEntry = shelfService.addShelfEntry(entry);
        return new ResponseEntity<>(newEntry, HttpStatus.CREATED);
    }

    // get all the shelf entries
    @GetMapping("/all")
    public ResponseEntity<List<Shelf>> getAllShelfEntries() {
        List<Shelf> shelfEntries = shelfService.getAllShelfEntries();
        return new ResponseEntity<>(shelfEntries, HttpStatus.OK);
    }

    // get a specific shelves
    @GetMapping("/{id}")
    public ResponseEntity<Shelf> getAShelfEntry(@PathVariable Long id) {
        Shelf shelfEntry = shelfService.getById(id);
        return new ResponseEntity<>(shelfEntry, HttpStatus.OK);
    }

    // get shelf entries by user
    @GetMapping("/user/{user}")
    public ResponseEntity<List<Shelf>> getShelfEntriesByUser(@RequestBody User user) {
        List<Shelf> shelfEntries = shelfService.getByUser(user);
        return new ResponseEntity<>(shelfEntries, HttpStatus.OK);
    }

    // get shelf entries by book
    @GetMapping("/book/{book}")
    public ResponseEntity<List<Shelf>> getShelfEntriesByBook(@RequestBody Book book) {
        List<Shelf> shelfEntries = shelfService.getByBook(book);
        return new ResponseEntity<>(shelfEntries, HttpStatus.OK);
    }

    // delete a specific shelf entry
    @DeleteMapping("/{id}")
    public ResponseEntity<Shelf> deleteAShelfEntry(@PathVariable Long id) {
        shelfService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
