package com.lucas.petshop.dto;

import com.lucas.petshop.service.RatingStarsEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RatingResponseDTO {
    RatingStarsEnum stars;
    String client;
    String comments;
    LocalDateTime createdAt;
}

