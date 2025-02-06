package com.emmlg.biblioapi.book.Repository;

import com.emmlg.biblioapi.book.model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    boolean existsByIsbn(
            @NotBlank(message = "El ISBN no puede estar vacio")
            @Size(min = 10, max = 13, message = "El ISBN debe tener entre 10 y 13 caracteres")
            String isbn);


    List<Book> getBooksByTitleContainingIgnoreCase(
            @NotBlank(message = "El título no puede estar vacio")
            @Size(min = 1, max = 255, message = "El título debe tener entre 1 y 255 caracteres")
            String title);
    List<Book> getBooksByAuthorContainingIgnoreCase(
            @NotBlank(message = "El título no puede estar vacio")
            @Size(min = 1, max = 255, message = "El título debe tener entre 1 y 255 caracteres")
            String author);


}
