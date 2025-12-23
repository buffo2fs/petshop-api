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

    @Autowired
    private RatingMapper ratingMapper;

    @Override
    public List<RatingResponseDTO> getAllRatings(){
        var startTime = System.currentTimeMillis();


        List<RatingResponseDTO> result = ratingRepository.findAll()
                .stream()
                .filter(rating -> !Boolean.TRUE.equals(rating.getDeletedRating()))
                .map(ratingMapper::toResponseDTO)
                .collect(Collectors.toList());

        Timer.measure("[GET ALL RATINGS] - Successfully", startTime);
        return result;
    }

    @Override
    public RatingResponseDTO getRatingById(Long id){
        var startTime = System.currentTimeMillis();

        Rating rating = getRatingIfExists(id);

        if(Boolean.TRUE.equals(rating.getDeletedRating())){
            throw new RuntimeException("RATING IS DELETED");
        }

        Timer.measure("[GET RATING BY ID] - Successfully", startTime);
        return ratingMapper.toResponseDTO(rating);
    }

    @Override
    @Transactional
    public void createRating(RatingRequestDTO dto) {
        var startTime = System.currentTimeMillis();

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND"));

        Rating rating = ratingMapper.toEntity(dto);

        rating.setProduct(product);
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

        ratingMapper.updateEntityFromDto(dto, existing);
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
        ratingRepository.save(existing);
    }

    private Rating getRatingIfExists(Long id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RATING NOT FOUND"));

    }
}
