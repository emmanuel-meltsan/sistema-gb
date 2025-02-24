package com.emmlg.biblioapi.book.service;

import com.emmlg.biblioapi.book.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBookService {

    BookDto createBook(BookDto bookDto);
    List<BookDto> getAllBooks();
    Page<BookDto> getAllBooks(Pageable pageable);
    List<BookDto> getBookByAuthor(String author);
    List<BookDto> getBookByTitle(String title);
    BookDto getBookById(Long idBook);
    BookDto updateBook(BookDto bookDto,Long idBook);
    void deleteBook(Long idBook);

}
