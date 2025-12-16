package com.lucas.petshop.dto;

/**
 * Response DTO for product-order associations returned by the API.
 *
 * <p>This record is an immutable, compact carrier for the values that describe a
 * product entry within an order: the product id, the order id, the quantity and
 * the unit price applied to that entry.</p>
 */
public record ProductOrderResponseDTO(
        // ID of the product referenced by this association
        Long productId,

        // ID of the order that contains the product
        Long orderId,

        // Quantity of the product in this order entry
        Integer quantity,

        // Unit price applied for this product in the order
        Double unitPrice
){}
