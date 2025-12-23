package com.lucas.petshop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderItemDTO {
    @NotNull
    Long productId;
    @NotNull
    @Min(1)
    Integer quantity;
}
