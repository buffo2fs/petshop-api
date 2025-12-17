package com.lucas.petshop.exception;

public class RatingNotFoundException extends RuntimeException {
    public RatingNotFoundException(String message) {
        super(message);
    }
}
