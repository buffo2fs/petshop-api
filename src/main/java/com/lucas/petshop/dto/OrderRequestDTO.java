package com.lucas.petshop.dto;

import com.lucas.petshop.service.OrderStatusEnum;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO{


        @NotBlank(message = "CLIENT IS REQUIRED")
        @Size(min = 5, max = 255, message = "CLIENT NAME SHOULD HAVE BETWEEN 5 TO 255 CHARACTERS")
        String client;

        @NotNull(message = "ORDER STATUS IS REQUIRED")
        OrderStatusEnum status;

        @NotEmpty(message = "THE ORDER MUST HAVE AT LEAST ONE ITEM")
        private List<ProductOrderItemDTO> items;
}

