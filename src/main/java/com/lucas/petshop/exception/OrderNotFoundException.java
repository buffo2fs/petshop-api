package com.lucas.petshop.exception;

/**
 * Thrown when an Order identified by a given id cannot be found.
 *
 * <p>This is an unchecked (runtime) exception intended to be thrown by the service
 * layer when a requested Order is missing. A web layer can map this exception to
 * an HTTP 404 Not Found response via a @ControllerAdvice handler.</p>
 */
public class OrderNotFoundException extends RuntimeException {

    // Create the exception with a human-readable message (e.g. "Order not found with id 123").
    public OrderNotFoundException(String message) {
        super(message);
    }
}
