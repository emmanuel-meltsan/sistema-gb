package com.emmlg.biblioapi.book.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {


    @Schema(description = "ID único del libro. Se genera automáticamente y es necesario para actualización y eliminación.",
            example = "1001",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @NotBlank(message = "book.title.notblank")
    @Size(min = 1, max = 255, message = "book.title.size")
    @Schema(description = "Título del libro", example = "Cien años de soledad")
    private String title;

    @NotBlank(message = "book.author.notblank")
    @Size(min = 1, max = 255, message = "book.author.size")
    @Schema(description = "Autor del libro", example = "Gabriel García Márquez")
    private String author;

    @NotBlank(message = "book.isbn.notblank")
    @Size(min = 10, max = 13, message = "book.isbn.size")
    @Schema(description = "Código ISBN del libro", example = "9780307474728")
    private String isbn;

    @PastOrPresent(message = "book.publishedDate.pastOrPresent")
    @Schema(description = "Fecha de publicación", example = "1967-06-05")
    private LocalDate publishedDate;

    @NotBlank(message = "book.genre.notblank")
    @Size(min = 1, max = 100, message = "book.genre.size")
    @Schema(description = "Género literario del libro",example = "Ciencia ficción")
    private String genre;

    @Min(value = 0, message = "book.stock.min")
    @Schema(description = "Cantidad de libros disponibles en stock", example = "50")
    private Long stockAvailable;
}
