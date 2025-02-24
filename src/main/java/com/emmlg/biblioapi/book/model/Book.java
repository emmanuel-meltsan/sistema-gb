package com.emmlg.biblioapi.book.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBook;

    private String title;

    private String author;

    private String isbn;

    private LocalDate publishedDate;

    private String genre;
    private long stockAvailable;

    @Override
    public String toString() {
        return "Book{" +
                "idBook=" + idBook +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publishedDate=" + publishedDate +
                ", genre='" + genre + '\'' +
                ", stockAvailable=" + stockAvailable +
                '}';
    }
}
