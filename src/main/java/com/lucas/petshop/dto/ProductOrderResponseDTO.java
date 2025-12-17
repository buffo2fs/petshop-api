package com.lucas.petshop.dto;

import java.math.BigDecimal;


public record ProductOrderResponseDTO(

        Long productOrderId,

        Long productId,

        Long orderId,

        Integer quantity,

        BigDecimal unitPrice
){}
