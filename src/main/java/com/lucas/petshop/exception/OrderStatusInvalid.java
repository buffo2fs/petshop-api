package com.lucas.petshop.exception;

/**
 * Thrown when an invalid order status value is encountered.
 *
 * <p>Use this unchecked exception in the service or validation layer when the
 * provided status cannot be mapped to a known OrderStatusEnum value or is not
 * allowed for the current operation. A web layer can map this exception to a
 * 400 Bad Request response via a @ControllerAdvice handler.</p>
 */
public class OrderStatusInvalid extends RuntimeException {

    // Construct the exception with a descriptive message (e.g. "Invalid status: FOO").
    public OrderStatusInvalid(String message) {
        super(message);
    }
}
