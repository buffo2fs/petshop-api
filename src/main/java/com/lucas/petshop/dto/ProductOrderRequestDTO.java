package com.lucas.petshop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductOrderRequestDTO (

        @NotNull(message = "PRODUCT ID IS REQUIRED")
        @Positive(message = "PRODUCT ID SHOULD BE GREATER THAN 0")
        Long productId,

        @NotNull(message = "ORDER ID IS REQUIRED")
        @Positive(message = "ORDER ID SHOULD BE GREATER THAN 0")
        Long orderId,

        @NotNull(message = "QUANTITY IS REQUIRED")
        @Positive(message = "QUANTITY SHOULD BE GREATER THAN 0")
        Integer quantity


){}
