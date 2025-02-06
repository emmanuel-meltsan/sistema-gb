package com.emmlg.biblioapi.book.controller;

import com.emmlg.biblioapi.book.dto.BookDto;
import com.emmlg.biblioapi.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;


    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody BookDto bookDto) {
        try {

            return new ResponseEntity<>("Book created : " +bookService.createBook(bookDto),
                    HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(defaultValue = "isbn") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending){

        try{
            Pageable pageable;
            if (page != null && size != null) {
                Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
                pageable = PageRequest.of(page, size, sort);
                return ResponseEntity.ok(bookService.getAllBooks(pageable));
            } else {
                return ResponseEntity.ok(bookService.getAllBooks());
            }

        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{idBook}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Integer idBook) {
        try{
            return ResponseEntity.ok(bookService.getBookById(idBook));
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND );
        }
    }
    @GetMapping("/search")
    public ResponseEntity<?> getbookBySearch(@RequestParam String query,@RequestParam String search) {
        try {
            if (search != null && !search.isEmpty() && query != null && !query.isEmpty()) {
                if (search.equalsIgnoreCase("author")) {
                    return ResponseEntity.ok(bookService.getBookByAuthor(query));
                } else if (search.equalsIgnoreCase("title")) {
                    return ResponseEntity.ok(bookService.getBookByTitle(query));
                } else {
                    throw new RuntimeException("Invalid search");
                }
            } else {
                throw new RuntimeException("Invalid search");
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{idBook}")
    public ResponseEntity<String> updateBook(@PathVariable long idBook,
                                             @RequestBody BookDto bookDto) {
     try {
         return ResponseEntity.ok("Actualizado con exito! "+bookService.updateBook(bookDto,idBook));
     }
     catch (Exception e) {
         return new ResponseEntity<>( e.getMessage(),HttpStatus.NOT_FOUND);
     }
    }

    @DeleteMapping("/{idBook}")
    public ResponseEntity<String> deleteBook(@PathVariable long idBook) {
       try {
           bookService.deleteBook(idBook);
           return ResponseEntity.ok("Eliminado con exito!");

       }catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }

}
