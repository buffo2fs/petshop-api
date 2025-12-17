package com.lucas.petshop.mapper;

import com.lucas.petshop.dto.RatingRequestDTO;
import com.lucas.petshop.dto.RatingResponseDTO;
import com.lucas.petshop.model.Product;
import com.lucas.petshop.model.Rating;

public class RatingMapper {

    public static RatingResponseDTO toDTO(Rating rating) {
        return new RatingResponseDTO(
                rating.getStars(),
                rating.getClient(),
                rating.getComments(),
                rating.getCreatedAt()
        );
    }

    public static Rating toEntity(RatingRequestDTO dto, Product product){
        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setStars(dto.stars());
        rating.setClient(dto.client());
        rating.setComments(dto.comments());

        return rating;
    }
}
