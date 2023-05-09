package com.book.bookshelf.models.author;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Component
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @SequenceGenerator(name = "author_sequence", sequenceName = "author_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_sequence")
    private Long id;

    @Column(name = "key")
    private String key;

    @Column(name = "name")
    private String name;
}