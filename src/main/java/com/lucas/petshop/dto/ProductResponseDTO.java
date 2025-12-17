package com.lucas.petshop.dto;

import com.lucas.petshop.service.ProductAnimalTypeEnum;
import com.lucas.petshop.service.ProductTypeEnum;

import java.math.BigDecimal;

/**
 * Response DTO for Product resources returned by the API.
 *
 * <p>This record is a compact immutable carrier used to expose product data from
 * the application to clients. It mirrors key fields from the Product entity but
 * is intentionally a separate type (DTO) to decouple internal models from the API.
 * </p>
 */
public record ProductResponseDTO (
        // Human-friendly product name
        String name,

        // Product type as defined by the application's ProductTypeEnum (e.g. FOOD, ACCESSORY)
        ProductTypeEnum type,

        // Target animal type for the product (e.g. DOG, CAT)
        ProductAnimalTypeEnum animalType,

        // Brand or manufacturer name
        String brand,

        // Descriptive text about the product
        String description,

        // Retail price for the product
        BigDecimal price,

        // Size or weight metric for the product (units depend on domain conventions)
        Double sizeWeight

){}
