package com.lucas.petshop.dto;

import com.lucas.petshop.service.OrderStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponseDTO {
    private Long id;

    private Integer totalItemsCount;

    private String client;

    private Double totalAmount;

    private OrderStatusEnum status;

    private LocalDateTime orderCreation;
}
