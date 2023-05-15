package com.project.gateway.exceptions;

public class ProductNotFountException extends RuntimeException {
    public ProductNotFountException(Long id) {
        super(String.format("Product with id: %d not found", id));
    }
}
