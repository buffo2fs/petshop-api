package com.lucas.petshop.service;

import com.lucas.petshop.dto.ProductOrderRequestDTO;
import com.lucas.petshop.dto.ProductOrderResponseDTO;
import com.lucas.petshop.dto.ProductOrderUpdateDTO;

import java.util.List;

/**
 * Service contract for managing ProductOrder associations (product lines inside orders).
 *
 * <p>Implementations of this interface provide business logic to list, create,
 * update and delete product-order entries. Controllers should depend on this
 * interface so the implementation can be tested and swapped independently.</p>
 */
public interface ProductOrderService {

    /**
     * Retrieve all product-order associations as response DTOs.
     *
     * @return list of {@link ProductOrderResponseDTO} representing active product-order entries
     */
    List<ProductOrderResponseDTO> getAllProductsOrders();

    /**
     * Retrieve a single product-order entry by its id.
     *
     * @param id database identifier of the product-order entry
     * @return a {@link ProductOrderResponseDTO} representing the entry
     */
    ProductOrderResponseDTO getProductsOrdersById(Long id);

    /**
     * Create a new product-order association from the provided request DTO.
     *
     * @param productsOrders DTO carrying required create data (productId, orderId, quantity, unitPrice)
     * @return id of the newly created product-order entry
     */
    long createProductOrder(ProductOrderRequestDTO productsOrders);

    /**
     * Update an existing product-order entry.
     *
     * @param id identifier of the entry to update
     * @param productsOrders DTO carrying updated values (validated by controller)
     */
    void updateProductOrder(Long id, ProductOrderUpdateDTO productsOrders);

    /**
     * Delete (soft or hard depending on implementation) a product-order entry by id.
     *
     * @param id identifier of the entry to delete
     */
    void deleteProductOrder(Long id);
}
