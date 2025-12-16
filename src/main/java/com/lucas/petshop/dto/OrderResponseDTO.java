package com.lucas.petshop.dto;

import com.lucas.petshop.service.OrderStatusEnum;

import java.time.LocalDateTime;

// Response DTO for Orders returned by the API
// Implemented as a record for an immutable, compact data carrier.
// Fields represent the commonly returned values for an order resource.
public record OrderResponseDTO (
        // Total number of items included in the order (e.g. sum of product quantities)
        Integer totalItemsCount,

        // Client name (person or company) who placed the order
        String client,

        // Total monetary amount for the order
        Double totalAmount,

        // Order status represented by the application's enum (e.g. PENDING, PAID)
        OrderStatusEnum status,

        // Timestamp when the order was created (server-side generated)
        LocalDateTime orderCreation
){}
