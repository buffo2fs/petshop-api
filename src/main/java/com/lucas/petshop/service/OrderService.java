package com.lucas.petshop.service;

import com.lucas.petshop.dto.OrderRequestDTO;
import com.lucas.petshop.dto.OrderResponseDTO;
import com.lucas.petshop.model.Order;

import java.util.List;

/**
 * Service contract for managing Order entities and the corresponding DTOs.
 *
 * <p>Implementations of this interface provide business logic for creating,
 * retrieving, updating, and deleting orders. Controllers should depend on
 * this interface (not implementations) to keep layers decoupled and testable.</p>
 */
public interface OrderService {

    /**
     * Retrieve all orders as response DTOs.
     *
     * @return a list of {@link OrderResponseDTO} representing all non-deleted orders
     */
    List<OrderResponseDTO> getAllOrders();

    /**
     * Retrieve a single order by its id as a response DTO.
     *
     * @param id the database identifier of the order
     * @return an {@link OrderResponseDTO} representing the order
     */
    OrderResponseDTO getOrderById(Long id);

    /**
     * Create a new order from the provided request DTO.
     *
     * @param order the incoming data used to create the order
     * @return the id of the newly created order
     */
    long createOrder(OrderRequestDTO order);

    /**
     * Update an existing order identified by id using the provided DTO.
     *
     * @param id the id of the order to update
     * @param order the new values for the order (validated by controller)
     */
    void updateOrder(Long id, OrderRequestDTO order);

    /**
     * Delete (soft or hard depending on implementation) the order with the given id.
     *
     * @param id the id of the order to delete
     */
    void deleteOrder(Long id);
}
