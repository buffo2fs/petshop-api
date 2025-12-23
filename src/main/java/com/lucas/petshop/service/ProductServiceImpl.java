package com.lucas.petshop.service;

import com.lucas.petshop.dto.ProductRequestDTO;
import com.lucas.petshop.dto.ProductResponseDTO;
import com.lucas.petshop.dto.ProductUpdateDTO;
import com.lucas.petshop.mapper.ProductMapper;
import com.lucas.petshop.model.Product;
import com.lucas.petshop.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.lucas.petshop.util.Timer;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(
            ProductRepository productRepository,
            ProductMapper productMapper
    ) {

        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }



    //GET ALL PRODUCTS
    @Override
    public List<ProductResponseDTO> getAllProducts(){
        long startTime = System.currentTimeMillis();

        List<ProductResponseDTO> result = productRepository.findAll()
                .stream()
                .filter(product -> !Boolean.TRUE.equals(product.getDeletedProduct()))
                .map(productMapper::toResponseDTO)
                .collect(Collectors.toList());

        Timer.measure("[GET ALL PRODUCTS - Successfully", startTime);

        return result;
    }

    //GET PRODUCTS BY ID
    @Override
    public ProductResponseDTO getProductById(Long id){
        long startTime = System.currentTimeMillis();

        Product product = getProductIfExists(id);

        if(Boolean.TRUE.equals(product.getDeletedProduct())){
            throw new RuntimeException("PRODUCT IS DELETED");
        }

        Timer.measure("[GET PRODUCT BY ID] - Successfully", startTime);

        return productMapper.toResponseDTO(product);
    }

    //CREATE PRODUCT
    @Override
    @Transactional
    public long createProduct(ProductRequestDTO dto){
        long startTime = System.currentTimeMillis();

        Product product = productMapper.toEntity(dto);
        Product savedProduct = productRepository.save(product);

        Timer.measure("[CREATE PRODUCT] - Successfully", startTime);

        return savedProduct.getId();
    }

    //UPDATE PRODUCT
    @Override
    @Transactional
    public void updateProduct(Long id, ProductRequestDTO dto){
        long startTime = System.currentTimeMillis();

        Product existing = getProductIfExists(id);

        if (Boolean.TRUE.equals(existing.getDeletedProduct())){
            throw new RuntimeException("CANNOT UPDATE A DELETED PRODUCT");
        }

        productMapper.updateEntityFromDto(dto, existing);
        existing.setLastUpdate(LocalDateTime.now());

        productRepository.save(existing);

        Timer.measure("[UPDATE PRODUCT] - Successfully", startTime);

    }

    //DELETE PRODUCT (SOFT DELETE)
    @Override
    public void deleteProduct(Long id){
        long startTime = System.currentTimeMillis();
        Product existing = getProductIfExists(id);

        if (Boolean.TRUE.equals(existing.getDeletedProduct())){
            throw new RuntimeException("PRODUCT ALREADY DELETED");
        }

        existing.setDeletedProduct(true);
        existing.setLastUpdate(LocalDateTime.now());

        productRepository.save(existing);

        Timer.measure("[DELETE PRODUCT] - Successfully", startTime);
    }


    @Override
    @Transactional
    public ProductResponseDTO partialUpdateProduct(Long id, ProductUpdateDTO dto){
        long startTime = System.currentTimeMillis();

        Product existing = getProductIfExists(id);

        if(Boolean.TRUE.equals(existing.getDeletedProduct())){
            throw new RuntimeException("CANNOT UPDATE A DELETED PRODUCT");
        }

        dto.applyTo(existing);

        productRepository.save(existing);

        Timer.measure("[PATCH PRODUCT] - Successfully", startTime);

        return productMapper.toResponseDTO(existing);
    }


    //METHODS
    private Product getProductIfExists(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("PRODUCT NOT FOUND"));
    }


}
