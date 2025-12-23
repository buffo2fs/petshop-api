package com.lucas.petshop.mapper;

import com.lucas.petshop.dto.OrderRequestDTO;
import com.lucas.petshop.dto.OrderResponseDTO;
import com.lucas.petshop.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponseDTO toResponseDTO(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderCreation", ignore = true)
    @Mapping(target = "orderUpdate", ignore = true)
    @Mapping(target = "deletedOrder", ignore = true)
    @Mapping(target = "productOrders", ignore = true)

    Order toEntity(OrderRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderCreation", ignore = true)
    @Mapping(target = "orderUpdate", ignore = true)
    @Mapping(target = "deletedOrder", ignore = true)
    @Mapping(target = "productOrders", ignore = true)
    void updateEntityFromDto(
            OrderRequestDTO dto,
            @MappingTarget Order order
    );

}
