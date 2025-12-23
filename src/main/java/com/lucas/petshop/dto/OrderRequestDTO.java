package com.lucas.petshop.dto;

import com.lucas.petshop.service.OrderStatusEnum;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO{


        @NotNull(message = "TOTAL ITEMS COUNT IS REQUIRED")
        @Positive(message = "TOTAL ITEMS COUNT SHOULD BE GRATER THAN 0 (ZERO)")
        Integer totalItemsCount;

        @NotBlank(message = "CLIENT IS REQUIRED")
        @Size(min = 5, max = 255, message = "CLIENT NAME SHOULD HAVE BETWEEN 5 TO 255 CHARACTERS")
        String client;

        @NotNull(message = "TOTAL AMOUNT IS REQUIRED")
        @Positive(message = "TOTAL AMOUNT SHOULD BE GREATER THAN 0 (ZERO)")
        Double totalAmount;

        @NotNull(message = "ORDER STATUS IS REQUIRED")
        OrderStatusEnum status;

        @NotEmpty(message = "THE ORDER MUST HAVE AT LEAST ONE ITEM")
        private List<ProductOrderItemDTO> items;
}

