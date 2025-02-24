package com.emmlg.biblioapi.book.response;

import com.emmlg.biblioapi.book.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeneralResponseJsonFormat {

    public ResponseEntity<Map<String, Object>> successResponse(String message,
                                                               BookDto bookDto,
                                                               HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", bookDto);
        response.put("message", message);
        response.put("errors", new ArrayList<>()); // Lista vacía porque no hay errores

        return ResponseEntity
                .status(status)
                .body(response);
    }

    public ResponseEntity<Map<String, Object>> successResponseList(String message,
                                                               List<BookDto> bookDto,
                                                               HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", bookDto);
        response.put("message", message);
        response.put("errors", new ArrayList<>()); // Lista vacía porque no hay errores

        return ResponseEntity
                .status(status)
                .body(response);
    }
    public ResponseEntity<Map<String, Object>> successResponseListPageble(
                                                                   String message,
                                                                   Page<BookDto> bookDto,
                                                                   HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", bookDto.getContent());
        response.put("message", message);
        response.put("errors", new ArrayList<>()); // Lista vacía porque no hay errores
        response.put("page_number",bookDto.getNumber());
        response.put("page_size",bookDto.getSize());
        response.put("total_pages",bookDto.getTotalPages()-1);
        response.put("total_element",bookDto.getTotalElements());

        return ResponseEntity
                .status(status)
                .body(response);
    }
}
