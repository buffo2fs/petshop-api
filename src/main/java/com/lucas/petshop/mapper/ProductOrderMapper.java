package com.lucas.petshop.mapper;

import com.lucas.petshop.dto.ProductOrderRequestDTO;
import com.lucas.petshop.dto.ProductOrderResponseDTO;
import com.lucas.petshop.dto.ProductOrderUpdateDTO;
import com.lucas.petshop.model.ProductOrder;

/**
 * Mapper for converting between ProductOrder entity and DTO representations.
 *
 * <p>This class centralizes transformation logic between the persistence model
 * (`ProductOrder`) and the API-facing data transfer objects. Keeping mapping in
 * a single place makes it easier to adjust field translations, maintain null
 * handling, and document the contract between layers.</p>
 */
public class ProductOrderMapper {

    /**
     * Convert a ProductOrder entity into a ProductOrderResponseDTO.
     *
     * Notes:
     * - The response DTO intentionally contains only the values required by API
     *   consumers (productId, orderId, quantity, unitPrice).
     * - This method performs a shallow copy of fields and does not expose the
     *   original entity to callers.
     */
    public static ProductOrderResponseDTO toDTO(ProductOrder entity){
        return new ProductOrderResponseDTO(
                entity.getProductId(),
                entity.getOrderId(),
                entity.getQuantity(),
                entity.getUnitPrice()
        );
    }

    /**
     * Create a ProductOrder entity from a ProductOrderRequestDTO (used for create operations).
     *
     * Notes:
     * - The produced entity is a new instance; persistence (save) is the
     *   responsibility of the service/repository layer.
     * - The request DTO is expected to provide all required fields (validated by
     *   controller-level @Valid). This mapper assumes the DTO has valid values.
     */
    public static ProductOrder toEntity(ProductOrderRequestDTO dto){
        ProductOrder entity = new ProductOrder();
        entity.setProductId(dto.productId());
        entity.setOrderId(dto.orderId());
        entity.setQuantity(dto.quantity());
        entity.setUnitPrice(dto.unitPrice());

        return entity;
    }

    /**
     * Create a ProductOrder entity from a ProductOrderUpdateDTO (used for update operations).
     *
     * Notes:
     * - This method produces a new entity populated from the update DTO. In
     *   some designs you might instead load the existing entity and apply only
     *   changed fields; choose the approach that matches your service semantics.
     * - Validation of the update DTO should be handled earlier (controller/service).
     */
    public static ProductOrder toEntity(ProductOrderUpdateDTO dto){
        ProductOrder entity = new ProductOrder();
        entity.setProductId(dto.productId());
        entity.setOrderId(dto.orderId());
        entity.setQuantity(dto.quantity());
        entity.setUnitPrice(dto.unitPrice());

        return entity;
    }

}
