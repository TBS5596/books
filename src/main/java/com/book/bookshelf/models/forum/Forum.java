package com.book.bookshelf.models.forum;

import com.book.bookshelf.models.book.Book;
import com.book.bookshelf.models.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "forums")
public class Forum {
    @Id
    @SequenceGenerator(name = "forum_sequence", sequenceName = "forum_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forum_sequence")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "admin")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "created", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate created = LocalDate.now();

    @Column(name = "no_of_users")
    private int noOfUsers = 1;

    public void setTitle() {
        this.title = "[" + this.id + "] " + this.book.getTitle() + "'s forum";
    }
}
