package com.lucas.petshop.service;


import com.lucas.petshop.dto.RatingRequestDTO;
import com.lucas.petshop.dto.RatingResponseDTO;
import com.lucas.petshop.mapper.RatingMapper;
import com.lucas.petshop.model.Product;
import com.lucas.petshop.model.Rating;
import com.lucas.petshop.repository.ProductRepository;
import com.lucas.petshop.repository.RatingRepository;
import com.lucas.petshop.util.Timer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<RatingResponseDTO> getAllRatings(){
        var startTime = System.currentTimeMillis();
        Timer.measure("[GET ALL RATINGS] - Successfully", startTime);

        return ratingRepository.findAll()
                .stream()
                .filter(rating -> !Boolean.TRUE.equals(rating.getDeletedRating()))
                .map(RatingMapper::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    public RatingResponseDTO getRatingById(Long id){
        var startTime = System.currentTimeMillis();

        Rating rating = getRatingIfExists(id);

        if(Boolean.TRUE.equals(rating.getDeletedRating())){
            throw new RuntimeException("RATING IS DELETED");
        }

        Timer.measure("[GET RATING BY ID] - Successfully", startTime);
        return RatingMapper.toDTO(rating);
    }

    @Override
    @Transactional
    public void createRating(RatingRequestDTO dto) {
        var startTime = System.currentTimeMillis();

        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND"));

        Rating rating = RatingMapper.toEntity(dto, product);
        ratingRepository.save(rating);

        Timer.measure("[CREATE RATING] - Successfully", startTime);
    }

    @Override
    @Transactional
    public void updateRating(Long id, RatingRequestDTO dto){
        var startTime = System.currentTimeMillis();

        Rating existing = getRatingIfExists(id);

        if(Boolean.TRUE.equals(existing.getDeletedRating())){
            throw new RuntimeException("CANNOT UPDATE A DELETED RATING");
        }

        updateEntityFromDto(existing, dto);
        existing.setLastUpdate(LocalDateTime.now());

        ratingRepository.save(existing);

        Timer.measure("[UPDATE RATING] - Successfully", startTime);
    }

    @Override
    @Transactional
    public void deleteRating(Long id){
        var startTime = System.currentTimeMillis();

        Rating existing = getRatingIfExists(id);

        if(Boolean.TRUE.equals(existing.getDeletedRating())){
            throw new RuntimeException("RATING ALREADY DELETED");
        }

        existing.setDeletedRating(true);
        existing.setLastUpdate(LocalDateTime.now());
    }

    private Rating getRatingIfExists(long id) {
        Rating rating = ratingRepository.getById(id);
        if (rating == null) {
            throw new RuntimeException("RATING NOT FOUND");
        }

        return rating;
    }

    private void updateEntityFromDto(Rating existing, RatingRequestDTO dto){

        existing.setStars(dto.stars());
        existing.setClient(dto.client());
        existing.setComments(dto.comments());
    }
}
