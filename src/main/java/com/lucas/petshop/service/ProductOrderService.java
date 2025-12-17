package com.lucas.petshop.service;

import com.lucas.petshop.dto.ProductOrderRequestDTO;
import com.lucas.petshop.dto.ProductOrderResponseDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ProductOrderService {

    List<ProductOrderResponseDTO> getProductsByOrder(Long orderId);

    ProductOrderResponseDTO getProductOrderById(Long id);



    void deleteProductOrder(Long orderId, Long productOrderId);

    void addProductToOrder (Long id, ProductOrderRequestDTO dto);

}
