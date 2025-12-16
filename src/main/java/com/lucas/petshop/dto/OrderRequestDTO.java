package com.lucas.petshop.dto;

import com.lucas.petshop.service.OrderStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.aspectj.weaver.ast.Not;

// DTO representing the payload for creating/updating an Order.
// Implemented as a Java record to provide an immutable data carrier with compact syntax.
// Validation annotations are applied to record components so Spring's @Valid can enforce them.
public record OrderRequestDTO(

        // Total number of items included in the order.
        // Must be present and a positive integer (> 0).
        @NotNull(message = "TOTAL ITEMS COUNT IS REQUIRED")
        @Positive(message = "TOTAL ITEMS COUNT SHOULD BE GRATER THAN 0 (ZERO)")
        Integer totalItemsCount,

        // Client name (person or company). Must be a non-blank string between 5 and 255 characters.
        // Use @NotBlank to disallow empty or whitespace-only values.
        @NotBlank(message = "CLIENT IS REQUIRED")
        @Size(min = 5, max = 255, message = "CLIENT NAME SHOULD HAVE BETWEEN 5 TO 255 CHARACTERS")
        String client,

        // Total order amount. Required and must be a positive number (> 0).
        @NotNull(message = "TOTAL AMOUNT IS REQUIRED")
        @Positive(message = "TOTAL AMOUNT SHOULD BE GREATER THAN 0 (ZERO)")
        Double totalAmount,

        // Order status represented by the application's OrderStatusEnum (e.g. PENDING, PAID).
        // This field is required; the enum ensures only valid statuses are accepted.
        @NotNull(message = "ORDER STATUS IS REQUIRED")
        OrderStatusEnum status
){}
