package com.lucas.petshop.service;

import com.lucas.petshop.dto.ProductRequestDTO;
import com.lucas.petshop.dto.ProductResponseDTO;
import com.lucas.petshop.dto.ProductUpdateDTO;

import java.util.List;

/**
 * Service contract for product-related business operations.
 *
 * <p>Implementations provide the application's business logic for CRUD and
 * partial-update operations on products. Controllers should depend on this
 * interface (not implementations) so the service can be mocked or replaced
 * during testing.</p>
 */
public interface ProductService {

    /**
     * Retrieve all products as response DTOs.
     * Implementations typically filter out soft-deleted products.
     *
     * @return list of {@link ProductResponseDTO} representing active products
     */
    List<ProductResponseDTO> getAllProducts();

    /**
     * Get a single product by its id.
     *
     * @param id the product database identifier
     * @return the {@link ProductResponseDTO} for the product
     */
    ProductResponseDTO getProductById(Long id);

    /**
     * Create a new product from the provided request DTO.
     *
     * @param product the incoming product data
     * @return the id of the created product
     */
    long createProduct(ProductRequestDTO product);

    /**
     * Update an existing product by id using data from the request DTO.
     * This is intended for full updates (replace semantics).
     *
     * @param id the id of the product to update
     * @param product the new product values
     */
    void updateProduct(Long id, ProductRequestDTO product);

    /**
     * Delete (soft or hard depending on implementation) a product by id.
     *
     * @param id the id of the product to delete
     */
    void deleteProduct(Long id);

    /**
     * Apply a partial update (patch) to a product using the provided DTO.
     * Only non-null fields in {@link ProductUpdateDTO} should be applied.
     *
     * @param id the id of the product to patch
     * @param product the patch data (fields optional)
     * @return the updated {@link ProductResponseDTO}
     */
    ProductResponseDTO partialUpdateProduct(Long id, ProductUpdateDTO product);
}
