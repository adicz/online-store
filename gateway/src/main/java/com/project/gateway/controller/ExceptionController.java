package com.project.gateway.controller;

import com.project.gateway.exceptions.ErrorMessage;
import com.project.gateway.exceptions.ProductNotFountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ProductNotFountException.class)
    public ResponseEntity<ErrorMessage> productNotFoundException(ProductNotFountException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder()
                        .status(HttpStatus.NOT_FOUND.toString())
                        .message(exception.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
