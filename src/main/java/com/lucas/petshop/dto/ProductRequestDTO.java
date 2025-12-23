package com.lucas.petshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import com.lucas.petshop.service.ProductTypeEnum;
import com.lucas.petshop.service.ProductAnimalTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDTO {

    @NotBlank(message = "PRODUCT NAME IS REQUIRED")
    @Size(min = 3, max = 80, message = "PRODUCT NAME SHOULD HAVE BETWEEN 3 TO 80 CHARACTERS")
    String name;

    @NotNull(message = "PRODUCT TYPE IS REQUIRED")
    ProductTypeEnum type;

    @NotNull(message = "PRODUCT ANIMAL TYPE IS REQUIRED")
    ProductAnimalTypeEnum animalType;

    @NotBlank(message = "PRODUCT BRAND IS REQUIRED")
    String brand;

    @NotBlank(message = "PRODUCT DESCRIPTION IS REQUIRED")
    @Size(min = 10, max = 255, message = "PRODUCT DESCRIPTION SHOULD HAVE BETWEEN 10 TO 255 CHARACTERS")
    String description;

    @NotNull(message = "PRODUCT STOCK IS REQUIRED")
    @Positive(message = "PRODUCT STOCK SHOULD BE GREATER THAN 0 (ZERO)")
    Integer stock;

    @NotNull(message = "PRODUCT PRICE IS REQUIRED")
    @Positive(message = "PRODUCT PRICE SHOULD BE GREATER THAN 0 (ZERO)")
    BigDecimal price;

    @NotNull(message = "PRODUCT SIZE AND/OR WEIGHT IS REQUIRED")
    @Positive(message = "PRODUCT SIZE AND/OR WEIGHT SHOULD BE GREATER THAN 0 (ZERO)")
    Double sizeWeight;
}
