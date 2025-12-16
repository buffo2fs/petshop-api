package com.lucas.petshop.mapper;

import com.lucas.petshop.dto.OrderRequestDTO;
import com.lucas.petshop.dto.OrderResponseDTO;
import com.lucas.petshop.model.Order;

/**
 * Mapper helper for converting between the `Order` entity and its DTO representations.
 *
 * <p>Keep mapping logic minimal and explicit here so controllers and services can
 * rely on simple data carriers. This class provides two convenience methods:
 * - `toDTO(Order)` converts an entity to the API response DTO
 * - `toEntity(OrderRequestDTO)` converts the incoming request DTO to a new entity
 *
 * Note: mapping may need to convert between enums and strings depending on how
 * request/response DTOs represent the status; adjust here if DTO types change.
 */
public class OrderMapper {

    /**
     * Convert an Order entity into an OrderResponseDTO.
     *
     * This method pulls the necessary fields from the entity and constructs a
     * DTO suitable for API responses. It deliberately copies values and does not
     * hold any reference to the entity (defensive mapping).
     *
     * If `Order` uses enums for status and your DTO expects a String, convert
     * the enum to `enum.name()` here. Currently both match the expected types.
     */
    public static OrderResponseDTO toDTO(Order order){
        return new OrderResponseDTO(
                order.getTotalItemsCount(),
                order.getClient(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getOrderCreation()
        );
    }

    /**
     * Create a new Order entity from an OrderRequestDTO.
     *
     * This builds a fresh entity instance populated with values from the request
     * object. It does NOT persist the entity — persistence should be handled by
     * the service/repository layer.
     *
     * If the request DTO provides strings for enum-backed fields, parse/convert
     * them to the corresponding enum values here (with validation and clear
     * error messages) before setting them on the entity.
     */
    public static Order toEntity(OrderRequestDTO dto){
        Order order = new Order();
        order.setTotalItemsCount(dto.totalItemsCount());
        order.setClient(dto.client());
        order.setTotalAmount(dto.totalAmount());
        order.setStatus(dto.status());

        return order;

    }
}
