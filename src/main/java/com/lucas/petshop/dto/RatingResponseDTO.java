package com.lucas.petshop.dto;

import com.lucas.petshop.service.RatingStarsEnum;

import java.time.LocalDateTime;

public record RatingResponseDTO(
        RatingStarsEnum stars,
        String client,
        String comments,
        LocalDateTime dateTime
) {}
