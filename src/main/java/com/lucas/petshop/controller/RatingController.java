package com.lucas.petshop.controller;

import com.lucas.petshop.dto.RatingRequestDTO;
import com.lucas.petshop.dto.RatingResponseDTO;
import com.lucas.petshop.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping()
    private ResponseEntity<List<RatingResponseDTO>> getAllRatings(){
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @GetMapping("/{id}")
    private ResponseEntity<RatingResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(ratingService.getRatingById(id));
    }

    @PostMapping
    private ResponseEntity<Void> createRating(@Valid @RequestBody RatingRequestDTO dto){
        ratingService.createRating(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<Void> updateRating(@PathVariable Long id, @Valid @RequestBody RatingRequestDTO dto){
        ratingService.updateRating(id, dto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteRating(@PathVariable Long id){
        ratingService.deleteRating(id);

        return ResponseEntity.noContent().build();
    }
}
