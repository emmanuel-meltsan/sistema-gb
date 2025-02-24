package com.emmlg.biblioapi.GlobalExceptionHandler;

import com.emmlg.biblioapi.book.exceptions.BooksMsgAlert;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.emmlg.biblioapi.book.exceptions.BooksMsgAlert.getMessageFromProperties;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BooksMsgAlert.class)
    public ResponseEntity<Map<String, Object>> handleBooksMsgAlert(BooksMsgAlert ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("data", new HashMap<>());
        errorResponse.put("message", ex.getMessage());

        List<Map<String, String>> errors = new ArrayList<>();
        Map<String, String> errorDetail = new HashMap<>();
        errorDetail.put("code", ex.getCode());
        errorDetail.put("description", ex.getMessage());
        errors.add(errorDetail);

        errorResponse.put("errors", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler({MethodArgumentNotValidException.class , ConstraintViolationException.class})
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("data", new HashMap<>());
        errorResponse.put("message", "Se encontraron errores de validación");

        List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> {
                    Map<String, String> err = new HashMap<>();
                    err.put("code", error.getField());

                    String message = getMessageFromProperties(error.getDefaultMessage(), error.getArguments());
                    err.put("description", message);

                    return err;
                })
                .collect(Collectors.toList());

        errorResponse.put("errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


}
