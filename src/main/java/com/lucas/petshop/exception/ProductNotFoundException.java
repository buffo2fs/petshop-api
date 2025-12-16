package com.lucas.petshop.exception;

/**
 * Exception thrown when a Product with a given id cannot be found.
 *
 * <p>This is an unchecked runtime exception intended to be used by the service
 * layer when a requested Product is missing. A controller advice can map this
 * exception to an HTTP 404 Not Found response for REST endpoints.</p>
 */
public class ProductNotFoundException extends RuntimeException {
    // Construct the exception with a descriptive message (e.g. "Product not found with id 123").
    public ProductNotFoundException(String message) {
        super(message);
    }
}
