package com.lucas.petshop.service;


import com.lucas.petshop.dto.OrderRequestDTO;
import com.lucas.petshop.dto.OrderResponseDTO;
import com.lucas.petshop.exception.OrderStatusInvalid;
import com.lucas.petshop.mapper.OrderMapper;
import com.lucas.petshop.repository.OrderRepository;
import com.lucas.petshop.model.Order;
import com.lucas.petshop.util.Timer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing Orders.
 *
 * <p>This class contains business logic to list, create, update and delete orders.
 * Methods are thin wrappers around the repository and keep timing/logging via
 * the {@link Timer} utility. Validation and mapping responsibilities are delegated
 * to DTOs, mappers, and the repository as appropriate.</p>
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Return all non-deleted orders as response DTOs.
     *
     * Notes:
     * - The repository returns all orders; we filter out those marked deleted
     *   so the API only exposes active orders.
     * - Mapping to DTOs is done in the {@link OrderMapper}.
     */
    @Override
    public List<OrderResponseDTO> getAllOrders() {
        var startTime = System.currentTimeMillis();
        Timer.measure("[GET ALL ORDERS] - Successfully", startTime);

        return orderRepository.findAll()
                .stream()
                // filter soft-deleted orders (deletedOrder true)
                .filter(order -> !Boolean.TRUE.equals(order.getDeletedOrder()))
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve a single order by id and return its DTO representation.
     *
     * Behavior notes:
     * - If the order is marked as deleted we throw a RuntimeException here
     *   (you could map that to a 404/410 in controller advice).
     */
    @Override
    public OrderResponseDTO getOrderById(Long id){
        var startTime = System.currentTimeMillis();

        Order order = getOrderIfExists(id);

        // if the order was soft-deleted, surface a clear error
        if (Boolean.TRUE.equals(order.getDeletedOrder())) {
            throw new RuntimeException("PRODUCT IS DELETED");
        }

        Timer.measure("[GET ORDER BY ID] - Successfully", startTime);
        return OrderMapper.toDTO(order);

    }

    /**
     * Create a new order from the provided request DTO.
     *
     * Notes:
     * - We validate that a status is present and throw {@link OrderStatusInvalid}
     *   if not. Mapping from DTO to entity is handled by {@link OrderMapper}.
     */
    @Override
    @Transactional
    public long createOrder(OrderRequestDTO dto){
        var startTime = System.currentTimeMillis();

        if(dto.status() == null) {
            throw new OrderStatusInvalid("STATUS IS REQUIRED");

        }
        Order orders = OrderMapper.toEntity(dto);
        Order saved = orderRepository.save(orders);

        Timer.measure("[CREATE ORDER] - Successfully", startTime);

        return saved.getId();
    }

    /**
     * Update an existing order.
     *
     * Behavior notes:
     * - If the order is already soft-deleted the update is not permitted.
     * - When the status is set to CANCELED the implementation marks the order
     *   as deleted (soft-delete) as a business rule.
     *  - Update timestamp
     */
    @Override
    @Transactional
    public void updateOrder(Long id, OrderRequestDTO dto) {
        var startTime = System.currentTimeMillis();

        Order existing = getOrderIfExists(id);

        if(Boolean.TRUE.equals(existing.getDeletedOrder())){
            throw new RuntimeException("CANNOT UPDATE A DELETED PRODUCT");
        }

        // Apply fields from DTO to the existing entity (mapping centralized below)
        updateEntityFromDto(existing, dto);


        // Business rule: canceled orders become soft-deleted
        if(dto.status() == OrderStatusEnum.CANCELED){
            existing.setDeletedOrder(true);
        }
        existing.setOrderUpdate(LocalDateTime.now());

        orderRepository.save(existing);

        Timer.measure("[UPDATE ORDER] - Successfully", startTime);
    }

    /**
     * Soft-delete an order by setting the deleted flag and updating the timestamp.
     */
    @Override
    public void deleteOrder(Long id) {
        var startTime = System.currentTimeMillis();

        Order existing = getOrderIfExists(id);

        if(Boolean.TRUE.equals(existing.getDeletedOrder())){
            throw new RuntimeException("ORDER ALREADY DELETED");
        }

        existing.setDeletedOrder(true);
        existing.setOrderUpdate(LocalDateTime.now());

        orderRepository.save(existing);

        Timer.measure("[DELETE ORDER] - Sucessfully", startTime);
    }

    /**
     * Helper: load an order by id or throw when absent.
     *
     * Note: this implementation uses {@link OrderRepository#getById(long)} which
     * may behave differently across JPA implementations (some throw if not found).
     * Consider using {@link OrderRepository#findById(Long)} and Optional handling
     * for a more explicit absent-case behavior.
     */
    private Order getOrderIfExists(long id) {
        Order order = orderRepository.getById(id);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }

        return order;
    }

    /**
     * Copy values from the incoming DTO into the existing entity instance.
     *
     * This method centralizes mapping logic for updates so validation and
     * business rules remain outside the mapper. It uses OrderMapper to create
     * a temporary entity from the DTO for any necessary conversions.
     */
    private void updateEntityFromDto(Order existing, OrderRequestDTO dto){

        Order fromDto = OrderMapper.toEntity(dto);

        existing.setTotalItemsCount(dto.totalItemsCount());
        existing.setClient(dto.client());
        existing.setTotalAmount(dto.totalAmount());
        existing.setStatus(dto.status());
        existing.setOrderUpdate(LocalDateTime.now());

    }
}
