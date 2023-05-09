package com.book.bookshelf.models.book;

import com.book.bookshelf.models.author.Author;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
@Table(name = "books")
public class Book {
    @Id
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @Column(name = "isbn_10")
    private List<String> isbn_10;

    @Column(name = "isbn_13")
    private List<String> isbn_13;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_work",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "work_id"))
    private List<Work> works;

    @ElementCollection
    @CollectionTable(name = "book_subjects", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "subject")
    private List<String> subjects;

    @Column(name = "publishers")
    private List<String> publishers;

    @Column(name = "publish_date")
    private String publish_date;

    @Column(name = "covers")
    private List<Integer> covers;

    public Book(String title, List<String> isbn_13) {
        this.title = title;
        this.isbn_13 = isbn_13;
    }
}