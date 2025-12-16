package com.lucas.petshop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * DTO used to update an existing ProductOrder entry.
 *
 * <p>This record contains the fields that can be updated for a product-order association:
 * productId, orderId, quantity and unitPrice. Validation annotations ensure all values
 * are provided and positive when used with @Valid in controller endpoints.</p>

 */
public record ProductOrderUpdateDTO (

        // ID of the product associated with this order entry.
        // Required and must be a positive long (> 0).
        @NotNull(message = "PRODUCT ID IS REQUIRED")
        @Positive(message = "PRODUCT ID SHOULD BE GREATER THAN ZERO (0)")
        Long productId,

        // ID of the order that contains the product.
        // Required and must be a positive long (> 0).
        @NotNull(message = "ORDER ID IS REQUIRED")
        @Positive(message = "ORDER ID SHOULD BE GREATER THAN ZERO (O)")
        Long orderId,

        // Quantity of the product in the order entry.
        // Required and must be a positive integer (> 0).
        @NotNull(message = "QUANTITY IS REQUIRED")
        @Positive(message = "QUANTITY SHOULD BE GREATER THAN ZERO(O)")
        Integer quantity,

        // Unit price for the product at the time of the order.
        // Required and must be a positive number (> 0).
        @NotNull(message = "PRICE IS REQUIRED")
        @Positive(message = "PRICE SHOULD BE GRATER THAN ZERO (O)")
        Double unitPrice
){}