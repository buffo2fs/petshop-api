package com.lucas.petshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import com.lucas.petshop.service.ProductTypeEnum;
import com.lucas.petshop.service.ProductAnimalTypeEnum;

import java.math.BigDecimal;

/**
 * Request DTO used to create or update Product entities via the API.
 *
 * <p>Implemented as a record for a compact, immutable data carrier. Validation
 * annotations on each component ensure incoming JSON is validated when used with
 * Spring's @Valid support in controller endpoints.</p>
 *
 * Fields:
 * - name: product name (string, required, 3-80 chars)
 * - type: product type enum (required)
 * - animalType: animal type enum (required)
 * - brand: product brand (string, required)
 * - description: product description (string, required, 10-255 chars)
 * - stock: inventory stock (integer, required, positive)
 * - price: product price (double, required, positive)
 * - sizeWeight: size/weight measure (double, required, positive)
 */
public record ProductRequestDTO (

        // Human-friendly product name. Required and must be between 3 and 80 characters.
        @NotBlank(message = "PRODUCT NAME IS REQUIRED")
        @Size(min = 3, max = 80, message = "PRODUCT NAME SHOULD HAVE BETWEEN 3 TO 80 CHARACTERS")
        String name,

        // Product type (application enum). Use ProductTypeEnum values (e.g. FOOD, ACCESSORY).
        // Declared as enum here to validate allowed values at deserialization time.
        @NotNull(message = "PRODUCT TYPE IS REQUIRED")
        ProductTypeEnum type,

        // Target animal type (application enum). Use ProductAnimalTypeEnum values (e.g. DOG, CAT).
        @NotNull(message = "PRODUCT ANIMAL TYPE IS REQUIRED")
        ProductAnimalTypeEnum animalType,

        // Brand of the product. Required non-empty string.
        @NotBlank(message = "PRODUCT BRAND IS REQUIRED")
        String brand,

        // Descriptive text for the product. Required and must be between 10 and 255 characters.
        @NotBlank(message = "PRODUCT DESCRIPTION IS REQUIRED")
        @Size(min = 10, max = 255, message = "PRODUCT DESCRIPTION SHOULD HAVE BETWEEN 10 TO 255 CHARACTERS")
        String description,

        // Available stock count. Required and must be a positive integer (> 0).
        @NotNull(message = "PRODUCT STOCK IS REQUIRED")
        @Positive(message = "PRODUCT STOCK SHOULD BE GREATER THAN 0 (ZERO)")
        Integer stock,

        // Product price. Required and must be a positive number (> 0).
        @NotNull(message = "PRODUCT PRICE IS REQUIRED")
        @Positive(message = "PRODUCT PRICE SHOULD BE GREATER THAN 0 (ZERO)")
        BigDecimal price,

        // Product size or weight measurement. Required and must be a positive number (> 0).
        @NotNull(message = "PRODUCT SIZE AND/OR WEIGHT IS REQUIRED")
        @Positive(message = "PRODUCT SIZE AND/OR WEIGHT SHOULD BE GREATER THAN 0 (ZERO)")
        Double sizeWeight
){}
