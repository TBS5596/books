package com.book.bookshelf.api;

import com.book.bookshelf.models.book.Book;
import com.book.bookshelf.models.book.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/books")
public class BookEndpoint {

    @Autowired
    private final BookService bookService;

    // get all the books
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // get a specific book by id
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    // get a specific book(s) by title
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title) {
        List<Book> books = bookService.getBooksByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // get a specific books by author
    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author) {
        List<Book> books = bookService.getBooksByAuthor(author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // get a specific books by subject
    @GetMapping("/subject/{subject}")
    public ResponseEntity<List<Book>> getBooksBySubject(@PathVariable String subject) {
        List<Book> books = bookService.getBooksBySubject(subject);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // get a specific book by isbn
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByISBN(@PathVariable String isbn) {
        Book book = new Book();
        List<String> isbnList = new ArrayList<String>();
        isbnList.add(isbn);
        if (isbn.length() == 10 || isbn.length() == 13) {
            book = bookService.getBookByISBN(isbnList);
            if (book == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    // add a new book
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book myBook = bookService.getBookByISBN(book.getIsbn_13());
        if (myBook == null) myBook = bookService.addBook(book);
        return new ResponseEntity<>(myBook, HttpStatus.OK);
    }

    // delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
