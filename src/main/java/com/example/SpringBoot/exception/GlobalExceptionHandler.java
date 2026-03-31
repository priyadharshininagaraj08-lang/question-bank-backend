package com.example.SpringBoot.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatePaperException.class)
    public ResponseEntity<String> handleDuplicate(DuplicatePaperException ex) {
        return ResponseEntity
                .status(400)
                .body(ex.getMessage());
    }
}