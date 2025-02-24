package com.emmlg.biblioapi.book.controller;

import com.emmlg.biblioapi.book.dto.BookDto;
import com.emmlg.biblioapi.book.response.GeneralResponseJsonFormat;
import com.emmlg.biblioapi.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.emmlg.biblioapi.book.exceptions.BooksMsgAlert.getMessageFromProperties;


@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/books")
@Tag(name = "Book API Service", description = "Enpoints de los servicios que ofrece Book Controller")
public class BookController {

    private final BookService bookService;
    private final GeneralResponseJsonFormat response;

    @PostMapping
    @Operation(summary = "Crear un nuevo libro",
            description = "Este endpoint crea un nuevo libro con los datos proporcionados.")
    public ResponseEntity <Map<String, Object>> createBook(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del libro a crear." +
                            "El ID se genera automáticamente y no debe enviarse.",
                    required = true)
            @Valid @RequestBody BookDto bookDto) {

        return response.successResponse(
                getMessageFromProperties("success.book.created"),
                bookService.createBook(bookDto),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    @Operation(summary = "Obtener todos los libros",
            description = "Obtiene una lista de todos los libros.")
    public ResponseEntity <Map<String, Object>> getAllBooks(){
        return
                response.successResponseList(
                        "Lista de Libros",
                        bookService.getAllBooks(),
                        HttpStatus.FOUND
                );
    }
    @GetMapping("/booksPaginados")
    @Operation(
            summary = "Obtiene los libros con paginación",
            description = "Este endpoint permite obtener una lista de libros paginados, con opciones para ordenar los resultados y especificar la página y el tamaño de la misma.",
            parameters = {
                    @Parameter(
                            name = "page",
                            description = "Número de página a obtener. Si no se especifica, se utilizará la página 0 por defecto.",
                            required = false,
                            example = "0"
                    ),
                    @Parameter(
                            name = "size",
                            description = "Número de elementos por página. Si no se especifica, se utilizará el valor por defecto (10).",
                            required = false,
                            example = "10"
                    ),
                    @Parameter(
                            name = "sortBy",
                            description = "Campo por el cual se ordenarán los libros. Por defecto es 'isbn'. Otros posibles valores: 'title', 'author'.",
                            required = false,
                            example = "isbn"
                    ),
                    @Parameter(
                            name = "ascending",
                            description = "Indica si el orden debe ser ascendente (true) o descendente (false). El valor predeterminado es 'true'.",
                            required = false,
                            example = "true"
                    )
            }
    )
    public ResponseEntity <Map<String, Object>> getAllBooks(
             @RequestParam(required = false) Integer page,
             @RequestParam(required = false) Integer size,
             @RequestParam(defaultValue = "isbn") String sortBy,
             @RequestParam(defaultValue = "true") boolean ascending){


            Pageable pageable;
           // if (page != null && size != null) {
                Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
                pageable = PageRequest.of(page, size, sort);

                return
                        response.successResponseListPageble(
                                "Lista de libros paginados",
                                bookService.getAllBooks(pageable),
                                HttpStatus.FOUND
                        );

    }

    @GetMapping("/{idBook}")
    @Operation(summary = "Obtener un libro por su ID",
            description = "Obtiene un libro utilizando su ID único.")
    public ResponseEntity<Map<String, Object>> getBookById(@PathVariable Long idBook) {

            return
                    response.successResponse(
                            "Libro Encontrado con exito!",
                            bookService.getBookById(idBook),
                            HttpStatus.FOUND
                    );

    }

    @GetMapping("/search/booktitle")
    @Operation(
            summary = "Busca un libro por título",
            description = "Este endpoint busca libros por el título .El título del libro es el parámetro de la solicitud.",
            parameters = {
                    @Parameter(
                            name = "titleofBook",
                            description = "Título del libro que se busca",
                            required = true,
                            example = "Cien años de soledad"
                    )})
    public ResponseEntity<Map<String, Object>>getbookByTittleOfBook(
            @RequestParam (required = true) String titleofBook) {


                return response.successResponseList(
                        "Author encontrado con exito!",
                        bookService.getBookByTitle(titleofBook),
                        HttpStatus.FOUND
                );


    }
    @GetMapping("/search/bookAuthor")
    @Operation(
            summary = "Busca libros por autor",
            description = "Este endpoint busca libros utilizando el nombre del autor.Especificar el nombre del autor como parámetro.",
            parameters = {
                    @Parameter(
                            name = "AuthorName",
                            description = "Nombre del autor que se busca",
                            required = true,
                            example = "Gabriel García Márquez"
                    )})
    public ResponseEntity<Map<String, Object>> getbookSearchByAuthor(

            @RequestParam(required = true) String AuthorName){

            return response.successResponseList(
                    "Autor Encontrado con exito!",
                    bookService.getBookByAuthor(AuthorName),
                    HttpStatus.FOUND
            );

    }

    @PutMapping("/{idBook}")
    @Operation(summary = "Actualizar un libro con su ID",
            description = "Actualiza los datos de un libro existente.")
    public ResponseEntity<Map<String, Object>> updateBook(
            @PathVariable Long idBook,
            @Valid @RequestBody BookDto bookDto) {

         return
                 response.successResponse(
                         "Actualizado con exito!",
                         bookService.updateBook(bookDto,idBook),
                         HttpStatus.CREATED
                 );

     }


    @DeleteMapping("/{idBook}")
    @Operation(summary = "Eliminar un libro",
            description = "Elimina un libro utilizando su ID único.")
    public ResponseEntity<Map<String, Object>>  deleteBook(@PathVariable long idBook) {

           bookService.deleteBook(idBook);

        return
        response.successResponse(
                "Eliminado con exito!",
                null,
                HttpStatus.OK
        );


    }

}
