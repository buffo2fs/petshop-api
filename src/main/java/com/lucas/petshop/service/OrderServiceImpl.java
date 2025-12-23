package com.lucas.petshop.service;


import com.lucas.petshop.dto.OrderRequestDTO;
import com.lucas.petshop.dto.OrderResponseDTO;
import com.lucas.petshop.mapper.OrderMapper;
import com.lucas.petshop.model.ProductOrder;
import com.lucas.petshop.repository.OrderRepository;
import com.lucas.petshop.model.Order;
import com.lucas.petshop.repository.ProductOrderRepository;
import com.lucas.petshop.repository.ProductRepository;
import com.lucas.petshop.util.Timer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;
    private final ProductOrderRepository productOrderRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            OrderMapper orderMapper,
            ProductRepository productRepository,
            ProductOrderRepository productOrderRepository
    ){
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productRepository = productRepository;
        this.productOrderRepository = productOrderRepository;
    }

    //GET ALL ORDERS
    @Override
    public List<OrderResponseDTO> getAllOrders() {
        long startTime = System.currentTimeMillis();

        List<OrderResponseDTO> result = orderRepository.findAll()
                .stream()
                .filter(order -> !Boolean.TRUE.equals(order.getDeletedOrder()))
                .map(orderMapper::toResponseDTO)
                .collect(Collectors.toList());

        Timer.measure("[GET ALL ORDERS] - Successfully", startTime);
        return result;
    }

    //GET ORDER BY ID

    @Override
    public OrderResponseDTO getOrderById(Long id){
        long startTime = System.currentTimeMillis();

        Order order = getOrderIfExists(id);

        if (Boolean.TRUE.equals(order.getDeletedOrder())) {
            throw new RuntimeException("ORDER IS DELETED");
        }

        Timer.measure("[GET ORDER BY ID] - Successfully", startTime);
        return orderMapper.toResponseDTO(order);

    }

    //CREATE ORDER
    @Override
    @Transactional
    public Long createOrder(OrderRequestDTO dto) {
        long startTime = System.currentTimeMillis();

        Order order = orderMapper.toEntity(dto);

        BigDecimal calculatedTotal = BigDecimal.ZERO;
        int calculatedItemsCount = 0;

        Order savedOrder = orderRepository.save(order);

        if (dto.getItems() != null && !dto.getItems().isEmpty()) {
            for (var itemDto : dto.getItems()) {
                var product = productRepository.findById(itemDto.getProductId())
                        .orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND: ID " + itemDto.getProductId()));

                ProductOrder productOrder = new ProductOrder();
                productOrder.setOrder(savedOrder);
                productOrder.setProduct(product);
                productOrder.setQuantity(itemDto.getQuantity());
                productOrder.setUnitPrice(product.getPrice()); // BigDecimal de tb_products

                productOrderRepository.save(productOrder);

                calculatedItemsCount += itemDto.getQuantity();

                BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity()));
                calculatedTotal = calculatedTotal.add(itemTotal);
            }
        }

        savedOrder.setTotalItemsCount(calculatedItemsCount);
        savedOrder.setTotalAmount(calculatedTotal.doubleValue());

        orderRepository.save(savedOrder);

        Timer.measure("[CREATE ORDER] - Successfully", startTime);
        return savedOrder.getId();
    }

    //UPDATE ORDER
    @Override
    @Transactional
    public void updateOrder(Long id, OrderRequestDTO dto) {
        long startTime = System.currentTimeMillis();

        Order existing = getOrderIfExists(id);

        if (Boolean.TRUE.equals(existing.getDeletedOrder())) {
            throw new RuntimeException("CANNOT UPDATE A DELETED ORDER");
        }

        productOrderRepository.deleteByOrderId(id);

        orderMapper.updateEntityFromDto(dto, existing);

        BigDecimal calculatedTotal = BigDecimal.ZERO;
        int calculatedItemsCount = 0;

        if (dto.getItems() != null && !dto.getItems().isEmpty()) {
            for (var itemDto : dto.getItems()) {
                var product = productRepository.findById(itemDto.getProductId())
                        .orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND: ID " + itemDto.getProductId()));

                ProductOrder productOrder = new ProductOrder();
                productOrder.setOrder(existing);
                productOrder.setProduct(product);
                productOrder.setQuantity(itemDto.getQuantity());
                productOrder.setUnitPrice(product.getPrice());

                productOrderRepository.save(productOrder);

                calculatedItemsCount += itemDto.getQuantity();
                BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity()));
                calculatedTotal = calculatedTotal.add(itemTotal);
            }
        }

        existing.setTotalItemsCount(calculatedItemsCount);
        existing.setTotalAmount(calculatedTotal.doubleValue());

        if (dto.getStatus() == OrderStatusEnum.CANCELED) {
            existing.setDeletedOrder(true);
        }

        existing.setOrderUpdate(LocalDateTime.now());
        orderRepository.save(existing);

        Timer.measure("[UPDATE ORDER] - Successfully", startTime);
    }


    //DELETE (SOFT DELETE)
    @Override
    public void deleteOrder(Long id) {
        long startTime = System.currentTimeMillis();

        Order existing = getOrderIfExists(id);

        if(Boolean.TRUE.equals(existing.getDeletedOrder())){
            throw new RuntimeException("ORDER ALREADY DELETED");
        }

        existing.setDeletedOrder(true);
        existing.setOrderUpdate(LocalDateTime.now());

        orderRepository.save(existing);

        Timer.measure("[DELETE ORDER] - Sucessfully", startTime);
    }

    //METHODS
    private Order getOrderIfExists(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("ORDER NOT FOUND"));
    }
}
