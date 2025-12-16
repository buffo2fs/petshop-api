package com.lucas.petshop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Request DTO for creating a ProductOrder (association between a product and an order).
 *
 * <p>This record is used as the request body for endpoints that create product-order
 * entries. It carries the minimal fields required to create an association: product id,
 * order id, quantity and unit price. Validation annotations ensure incoming values are
 * present and positive.</p>
 *
 * <p>Example JSON:
 * <pre>
 * {
 *   "productId": 1,
 *   "orderId": 2,
 *   "quantity": 3,
 *   "unitPrice": 19.99
 * }
 * </pre>
 * </p>
 *
 */
// DTO representing a request to create a ProductOrder (association between a product and an order).
// Implemented as a record: immutable data carrier where components represent the required payload fields.
// Validation annotations ensure incoming JSON is validated when used with @Valid in controllers.
public record ProductOrderRequestDTO (

        // ID of the product being added to the order.
        // Must be present and a positive number (> 0).
        @NotNull(message = "PRODUCT ID IS REQUIRED")
        @Positive(message = "PRODUCT ID SHOULD BE GREATER THAN 0 (ZERO)")
        Long productId,

        // ID of the order to which the product belongs.
        // Must be present and a positive number (> 0).
        @NotNull(message = "ORDER ID IS REQUIRED")
        @Positive(message = "ORDER ID SHOULD BE GREATER THAN 0 (ZERO)")
        Long orderId,

        // Quantity of the product in this order entry.
        // Must be present and a positive integer (> 0).
        @NotNull(message = "QUANTITY IS REQUIRED")
        @Positive(message = "QUANTITY SHOULD BE GREATER THAN 0 (ZERO)")
        Integer quantity,

        // Unit price for the product at the time of the order.
        // Required and must be a positive number (> 0).
        @NotNull(message = "UNIT PRICE IS REQUIRED")
        @Positive(message = "UNIT PRICE SHOULD BE GREATER THAN 0 (ZERO)")
        Double unitPrice
){}
