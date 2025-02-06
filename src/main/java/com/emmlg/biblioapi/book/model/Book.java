package com.emmlg.biblioapi.book.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {

    private long idBook;
    private String title;
    private String author;
    private String isbn;
    private LocalDate publishedDate;
    private String genre;
    private long stockAvailable;

}
