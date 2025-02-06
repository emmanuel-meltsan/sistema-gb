package com.emmlg.biblioapi.book.dto;

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

    @NotBlank(message = "El título no puede estar vacio")
    @Size(min = 1, max = 255, message = "El título debe tener entre 1 y 255 caracteres")
    private String title;

    @NotBlank(message = "El autor no puede estar vacio")
    @Size(min = 1, max = 255, message = "El autor debe tener entre 1 y 255 caracteres")
    private String author;

    @NotBlank(message = "El ISBN no puede estar vacio")
    @Size(min = 10, max = 13, message = "El ISBN debe tener entre 10 y 13 caracteres")
    private String isbn;

    @PastOrPresent(message = "La fecha de publicación no puede ser superior al dia de hoy")
    private LocalDate publishedDate;

    @NotBlank(message = "El género no puede estar vacio")
    @Size(min = 1, max = 100, message = "El género debe tener entre 1 y 100 caracteres")
    private String genre;

    @Min(value = 0, message = "El stock disponible no puede ser negativo")
    private long stockAvailable;
}
