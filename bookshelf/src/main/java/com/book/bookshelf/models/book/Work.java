package com.book.bookshelf.models.book;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "works")
public class Work {
    @Id
    @SequenceGenerator(name = "work_sequence", sequenceName = "work_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_sequence")
    private Long id;

    @Column(name = "key")
    private String key;
}