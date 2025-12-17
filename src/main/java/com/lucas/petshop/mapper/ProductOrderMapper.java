package com.lucas.petshop.mapper;

import com.lucas.petshop.dto.ProductOrderResponseDTO;
import com.lucas.petshop.model.ProductOrder;

public class ProductOrderMapper {

    private ProductOrderMapper() {
        // evita instanciação
    }

    public static ProductOrderResponseDTO toDTO(ProductOrder entity) {

        return new ProductOrderResponseDTO(
                entity.getId(),
                entity.getProduct().getId(),
                entity.getOrder().getId(),
                entity.getQuantity(),
                entity.getUnitPrice()
        );
    }
}