package com.lucas.petshop.dto;

import com.lucas.petshop.enums.RatingStarsEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RatingResponseDTO {
    Long product;
    RatingStarsEnum stars;
    String client;
    String comments;
    LocalDateTime createdAt;
}

