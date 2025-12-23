package com.lucas.petshop.mapper;

import com.lucas.petshop.dto.RatingRequestDTO;
import com.lucas.petshop.dto.RatingResponseDTO;
import com.lucas.petshop.model.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper (componentModel = "spring")
public interface RatingMapper {

    RatingResponseDTO toResponseDTO(Rating rating);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdate", ignore = true)
    @Mapping(target = "deletedRating", ignore = true)
    @Mapping(target = "product", ignore = true)
    Rating toEntity(RatingRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdate", ignore = true)
    @Mapping(target = "deletedRating", ignore = true)
    void updateEntityFromDto(RatingRequestDTO dto, @MappingTarget Rating rating);
}
