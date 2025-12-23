package com.lucas.petshop.mapper;

import com.lucas.petshop.dto.ProductRequestDTO;
import com.lucas.petshop.dto.ProductResponseDTO;
import com.lucas.petshop.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDTO toResponseDTO(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdate", ignore = true)
    @Mapping(target = "deletedProduct", ignore = true)
    Product toEntity(ProductRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastUpdate", ignore = true)
    @Mapping(target = "deletedProduct", ignore = true)
    void updateEntityFromDto(
            ProductRequestDTO dto,
            @MappingTarget Product product
    );
}
