package com.lucas.petshop.service;

import com.lucas.petshop.dto.OrderRequestDTO;
import com.lucas.petshop.dto.OrderResponseDTO;

import java.util.List;


public interface OrderService {

    List<OrderResponseDTO> getAllOrders();


    OrderResponseDTO getOrderById(Long id);

    Long createOrder(OrderRequestDTO order);

    void updateOrder(Long id, OrderRequestDTO order);

    void deleteOrder(Long id);
}
