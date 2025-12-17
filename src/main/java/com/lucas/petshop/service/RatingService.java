package com.lucas.petshop.service;

import com.lucas.petshop.dto.RatingRequestDTO;
import com.lucas.petshop.dto.RatingResponseDTO;

import java.util.List;

public interface RatingService {

    List<RatingResponseDTO> getAllRatings();

    RatingResponseDTO getRatingById(Long id);

    void createRating (RatingRequestDTO rating);

    void updateRating (Long id, RatingRequestDTO rating);

    void deleteRating (Long id);

}


