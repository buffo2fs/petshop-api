package com.lucas.petshop.service;

import com.lucas.petshop.dto.ProductRequestDTO;
import com.lucas.petshop.mapper.ProductMapper;
import com.lucas.petshop.model.Product;
import com.lucas.petshop.repository.ProductRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Test
    void shouldCreateProductSuccessfully(){

        ProductRequestDTO dto = new ProductRequestDTO();
        Product product = new Product();
        product.setId(1L);

        when(productMapper.toEntity(dto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        long result = productServiceImpl.createProduct(dto);


        assertEquals(1L, result);

        verify(productMapper).toEntity(dto);
        verify(productRepository).save(product);

    }

    @Test
    void shouldThrowExceptionWhenMapperFails(){

        ProductRequestDTO dto = new ProductRequestDTO();

        when(productMapper.toEntity(dto))
                .thenThrow(new IllegalArgumentException("Invalid product"));

        assertThrows(IllegalArgumentException.class,
                ()-> productServiceImpl.createProduct(dto));

        verify(productRepository, never()).save(any());

    }

}

