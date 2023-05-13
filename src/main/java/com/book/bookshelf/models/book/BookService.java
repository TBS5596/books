package com.book.bookshelf.models.book;

import com.book.bookshelf.config.Constants;
import com.book.bookshelf.config.LocalDateAdapter;
import com.book.bookshelf.models.author.Author;
import com.book.bookshelf.models.author.AuthorRepository;
import com.book.bookshelf.models.author.AuthorService;
import com.book.bookshelf.models.openlib.OpenLibRequest;
import com.book.bookshelf.models.openlib.WorkInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class BookService {
    @Autowired
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;
    private final OpenLibRequest openLibRequest;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> getBooksBySubject(String subject) {
        return bookRepository.findBySubjects(subject);
    }

    public Book getBookByISBN(List<String> isbn) {
        return bookRepository.findByIsbn_10(isbn)
                .orElseGet(() -> bookRepository.findByIsbn_13(isbn).orElse(null));
    }

    public Book addBook(Book book) {
        String bookInfoAsString = openLibRequest.getObjectAsString(Constants.get_book_by_isbn_url + book.getIsbn_13().get(0) + ".json");
        // check if a response was given the api endpoint
        if (bookInfoAsString != null) {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            // transform the book info string into a book info object
            Book bookInfo = gson.fromJson(bookInfoAsString, Book.class);
            if (bookInfo == null) {
                book.setDescription("OpenLibrary API cannot find information about the book. Manually insert book " +
                        "information");
                return bookRepository.save(book);
            }
            // get works information
            String works_ext_url = bookInfo.getWorks().get(0).getKey();
            String worksInfoAsString =
                    openLibRequest.getObjectAsString(Constants.openlibrary_url + works_ext_url +
                            ".json");
            WorkInfo workInfo = gson.fromJson(worksInfoAsString, WorkInfo.class);
            // create a list of book authors
            List<Author> authors = new ArrayList<Author>();
            if (workInfo.getAuthors() != null) {
                for (WorkInfo.Author author : workInfo.getAuthors()) {
                    Author workInfoAuthor = new Author();
                    workInfoAuthor.setKey(author.toString());
                    authors.add(workInfoAuthor);
                }
                for (Author author : authors) {
                    // check if the author already exists
                    Optional<Author> authorOptional = authorRepository.findByKey(author.getKey());
                    if (authorOptional.isPresent()) author = authorOptional.get();
                    else {
                        // get information about the new author
                        String AuthorInfoAsString =
                                openLibRequest.getObjectAsString(Constants.openlibrary_url + author.getKey() + ".json");
                        // transform the author info string into an author info object
                        Author openLibAuthor = gson.fromJson(AuthorInfoAsString, Author.class);
                        // set author name
                        author.setName(openLibAuthor.getName());
                    }
                }
            } else {
                // retrieve the default author
                Author author = authorService.getAuthorById(1L);
                authors.add(author);
            }
            // add full list of authors
            bookInfo.setAuthors(authors);
            // add the subject and description
            bookInfo.setSubjects(workInfo.getSubjects());
            bookInfo.setDescription(workInfo.getDescription());
            // update the title
            bookInfo.setTitle(workInfo.getTitle());
            // save to database
            return bookRepository.save(bookInfo);
        } else return null;
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
