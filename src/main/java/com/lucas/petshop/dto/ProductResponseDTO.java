package com.lucas.petshop.dto;

import com.lucas.petshop.service.ProductAnimalTypeEnum;
import com.lucas.petshop.service.ProductTypeEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDTO {
    String name;

    ProductTypeEnum type;

    ProductAnimalTypeEnum animalType;

    String brand;

    String description;

    BigDecimal price;

    Double sizeWeight;
}


