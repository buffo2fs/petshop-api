package com.lucas.petshop.dto;

import com.lucas.petshop.service.RatingStarsEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RatingRequestDTO(

        @NotNull(message = "PRODUCT ID IS REQUIRED")
        @Positive(message = "PRODUCT ID SHOULD BE GRATER THAN 0 (ZERO)")
        Long productId,

        @NotNull(message = "INFORM AT LEAST ONE STAR")
        RatingStarsEnum stars,

        @NotBlank(message = "CLIENT NAME IS REQUIRED")
        @Size(min = 3, max = 50, message = "CLIENT NAME SHOULD HAVE BETWEEN 3 TO 50 CHARACTERS")
        String client,

        @Size(max = 255, message = "COMMENTS MUST NOT EXCEED 255 CHARACTERS")
        String comments

) {}

