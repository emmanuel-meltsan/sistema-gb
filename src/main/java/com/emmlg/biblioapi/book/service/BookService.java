package com.emmlg.biblioapi.book.service;

import com.emmlg.biblioapi.book.Repository.BookRepository;
import com.emmlg.biblioapi.book.dto.BookDto;
import com.emmlg.biblioapi.book.exceptions.BooksMsgAlert;
import com.emmlg.biblioapi.book.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {


private final BookRepository bookRepository;

/* CREATE To-Do
* - verificar si no existe por isbn,titulo
* - verificar si existe el autor
* - año de la publicacion

 */
@Override
public BookDto createBook(BookDto bookDto) {

    if (bookRepository.existsByIsbn(bookDto.getIsbn())) {
        throw new BooksMsgAlert("El libro ya existe con el ISBN: " + bookDto.getIsbn());
    }

    Book newBook =convertToEntity(bookDto);
    bookRepository.save(newBook);

    return bookDto;
}

    /** READ : To-Do
     *  - leer todo los libros y sus autores sin ciclarlo
     *  - leer por id
     *  devolver los libros con paginacion
     */
    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book b : books.reversed()) {
            BookDto bdto = convertToDto(b);
            bookDtos.add(bdto);
        }
        return bookDtos;
    }


@Override
public Page<BookDto> getAllBooks(Pageable pageable) {
    Page<Book> books = bookRepository.findAll(pageable);
    if (books.getTotalElements() == 0) {
        throw new BooksMsgAlert("Libros no encontrados");
    }
    List<BookDto> bookDtos = new ArrayList<>();
    for (Book b : books.getContent()) {
        BookDto bdto = convertToDto(b);
        bookDtos.add(bdto);
    }
    //  Page<BookDto> bookDtoPage = new PageImpl<>(bookDtos, pageable,books.getTotalElements());

    return new PageImpl<>(bookDtos, pageable, books.getTotalElements());
}

    public List<BookDto> getBookByAuthor(String author){

     List<Book> booksfromauthor = bookRepository.getBooksByAuthorContainingIgnoreCase(author);
     if(booksfromauthor.isEmpty()){
         throw new BooksMsgAlert("no hay libros con ese author");
     }
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book b : booksfromauthor) {
            BookDto bdto = convertToDto(b);
            bookDtos.add(bdto);

        }


    return bookDtos;
    }
    public List<BookDto> getBookByTitle(String title){
    List<Book> booksfromTitle = bookRepository.getBooksByTitleContainingIgnoreCase(title);
    if(booksfromTitle.isEmpty()){
        throw new BooksMsgAlert("No hay libros con ese titulo");
    }
    List<BookDto> bookDtos = new ArrayList<>();
    for (Book b : booksfromTitle) {
        BookDto bdto = convertToDto(b);
        bookDtos.add(bdto);

    }
    return bookDtos;
    }

    @Override
    public BookDto getBookById(long idBook) {
    Book book = bookRepository.findById(idBook).
            orElseThrow(()-> new BooksMsgAlert("El libro no existe con el ID: " + idBook));
    return convertToDto(book);
    }


 /* UPDATE To-Do
 * - verificar si existe el libro
 * - pedir que cree el autor primero si no existe
 * - solo actualizar datos del libro o autor con su id
 *
 */
    @Override
    public BookDto updateBook(BookDto bookDto, long idBook) {
        Book newBook = bookRepository.findById(idBook).
                orElseThrow(()-> new BooksMsgAlert("El libro no existe con el ID: " +idBook));

        newBook.setTitle(bookDto.getTitle());
        newBook.setAuthor(bookDto.getAuthor());
        newBook.setIsbn(bookDto.getIsbn());
        newBook.setPublishedDate(bookDto.getPublishedDate());
        newBook.setGenre(bookDto.getGenre());
        newBook.setStockAvailable(bookDto.getStockAvailable());
        bookRepository.save(newBook);

        return bookDto;
    }

    @Override
    public void deleteBook(long idBook) {
    Book nbook = bookRepository.findById(idBook).
        orElseThrow(()-> new BooksMsgAlert("Libro no existe con el ID: " + idBook));
    bookRepository.delete(nbook);
    }

    private BookDto convertToDto(Book book) {
        BookDto bdto = new BookDto();
        bdto.setIsbn(book.getIsbn());
        bdto.setTitle(book.getTitle());
        bdto.setAuthor(book.getAuthor());
        bdto.setPublishedDate(book.getPublishedDate());
        bdto.setGenre(book.getGenre());
        bdto.setStockAvailable(book.getStockAvailable());

        return bdto;
    }
    private Book convertToEntity(BookDto bookDto) {
        Book book = new Book();

        book.setIsbn(bookDto.getIsbn());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPublishedDate(bookDto.getPublishedDate());
        book.setGenre(bookDto.getGenre());
        book.setStockAvailable(bookDto.getStockAvailable());

        return book;
    }

}


