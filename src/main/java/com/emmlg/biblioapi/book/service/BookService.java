package com.emmlg.biblioapi.book.service;

import com.emmlg.biblioapi.book.Repository.BookRepository;
import com.emmlg.biblioapi.book.dto.BookDto;
import com.emmlg.biblioapi.book.exceptions.BooksMsgAlert;
import com.emmlg.biblioapi.book.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.emmlg.biblioapi.book.exceptions.BooksMsgAlert.getMessageFromProperties;

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
        throw new BooksMsgAlert(
                getMessageFromProperties("error.isbnExist.code"),
                getMessageFromProperties("error.isbn.exists", bookDto.getIsbn())
        );

    }

    Book newBook = convertToEntity(bookDto);


    return convertToDto(bookRepository.save(newBook));
}

    /** READ : To-Do
     *  - leer todo los libros y sus autores sin ciclarlo
     *  - leer por id
     *  devolver los libros con paginacion
     */
    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        if(books.isEmpty()) {
            throw new BooksMsgAlert(
                    getMessageFromProperties("error.books.code"),
                    "no hay libros Disponibles todavia"
            );
        }
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
    System.out.println("----");
    System.out.println(pageable);
    System.out.println("---");
    System.out.println(books.toString());
    if (books.getTotalElements() == 0) {
        throw new BooksMsgAlert(
                "error.books.code",
                "Libros no encontrados");
    }
    List<BookDto> bookDtos = new ArrayList<>();
    for (Book b : books.getContent()) {
        BookDto bdto = convertToDto(b);
        bookDtos.add(bdto);
    }
    //  Page<BookDto> bookDtoPage = new PageImpl<>(bookDtos, pageable,books.getTotalElements());

    return new PageImpl<>(bookDtos, pageable,books.getTotalElements());
}

    public List<BookDto> getBookByAuthor(String author){

        author = author.trim();
        if( author.trim().isEmpty()){
            throw new BooksMsgAlert(
                    getMessageFromProperties("book.author.notblank"),
                    "EL autor debe de tener entre 3 caracteres hasta 255"

                    );
        }
     List<Book> booksfromauthor = bookRepository.getBooksByAuthorContainingIgnoreCase(author);
     if(booksfromauthor.isEmpty()){
         throw new BooksMsgAlert(
                 getMessageFromProperties("error.author.code"),
                 getMessageFromProperties("error.author.notfound" ,author)

         );

     }
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book b : booksfromauthor) {
            BookDto bdto = convertToDto(b);
            bookDtos.add(bdto);

        }


    return bookDtos;
    }

    public List<BookDto> getBookByTitle(String title){

        title = title.trim();

        if( title.trim().isEmpty()){
            throw new BooksMsgAlert(
                    "Titulo Vacio",
                    "El titulo no debe de estar Vacio"
            );
        }
        List<Book> booksfromTitle = bookRepository.getBooksByTitleContainingIgnoreCase(title);
    if(booksfromTitle.isEmpty()){
        throw new BooksMsgAlert(
                getMessageFromProperties("error.title.code"),
                getMessageFromProperties("error.title.notfound",title)
        );
    }
    List<BookDto> bookDtos = new ArrayList<>();
    for (Book b : booksfromTitle) {
        BookDto bdto = convertToDto(b);
        bookDtos.add(bdto);

    }
    return bookDtos;
    }

    @Override
    public BookDto getBookById(Long idBook) {
        if(idBook.longValue()> Long.MAX_VALUE){
            throw new BooksMsgAlert(
                    "error.bookid.code",
                    "El numero es muy largo"
            );
        }
    Book book = bookRepository.findById(idBook).
            orElseThrow(()-> new BooksMsgAlert(
                    getMessageFromProperties("error.bookid.code"),
                    getMessageFromProperties("error.bookid.notfound",idBook)
            ));
    return convertToDto(book);
    }


 /* UPDATE To-Do
 * - verificar si existe el libro
 * - pedir que cree el autor primero si no existe
 * - solo actualizar datos del libro o autor con su id
 *
 */
    @Override
    public BookDto updateBook(BookDto bookDto, Long idBook) {
        Book newBook = bookRepository.findById(idBook).
                orElseThrow(()-> new BooksMsgAlert(
                        getMessageFromProperties("error.bookid.code"),
                        getMessageFromProperties("error.bookid.notfound",idBook)
                ));

        newBook.setIdBook(idBook);
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
    public void deleteBook(Long idBook) {
    Book nbook = bookRepository.findById(idBook).
        orElseThrow(()-> new BooksMsgAlert(

                getMessageFromProperties("error.bookid.code"),
          "Error al eliminar"+getMessageFromProperties("error.bookid.notfound",idBook)
                ));
    bookRepository.delete(nbook);
    }

    private BookDto convertToDto(Book book) {
        BookDto bdto = new BookDto();
        bdto.setId(book.getIdBook());// check
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

        book.setIdBook(bookDto.getId());
        book.setIsbn(bookDto.getIsbn());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPublishedDate(bookDto.getPublishedDate());
        book.setGenre(bookDto.getGenre());
        book.setStockAvailable(bookDto.getStockAvailable());

        return book;
    }

}


