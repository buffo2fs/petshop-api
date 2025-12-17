package com.lucas.petshop.service;

import com.lucas.petshop.dto.ProductRequestDTO;
import com.lucas.petshop.dto.ProductResponseDTO;
import com.lucas.petshop.dto.ProductUpdateDTO;
import com.lucas.petshop.mapper.ProductMapper;
import com.lucas.petshop.model.Product;
import com.lucas.petshop.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lucas.petshop.util.Timer;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation that contains business logic for Product management.
 *
 * <p>This class performs typical CRUD and partial-update operations using the
 * {@link ProductRepository} for persistence and {@link ProductMapper} for
 * mapping between entities and DTOs. Methods use {@link Timer} for simple
 * timing instrumentation. The class enforces soft-delete rules (products marked
 * deleted are excluded from normal operations).</p>
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Retrieve all non-deleted products as DTOs.
     *
     * Behavior notes:
     * - Filters out products where `deletedProduct` is true (soft-deleted).
     * - Maps entities to {@link ProductResponseDTO} for API consumption.
     */
    @Override
    public List<ProductResponseDTO> getAllProducts(){
        var startTime = System.currentTimeMillis();
        Timer.measure("[GET ALL PRODUCTS - Successfully", startTime);

        return productRepository.findAll()
                .stream()
                .filter(product -> !Boolean.TRUE.equals(product.getDeletedProduct()))
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get a single product by id and return its DTO.
     *
     * Validation/behavior:
     * - Throws a RuntimeException when a product is soft-deleted.
     * - Uses helper {@link #getProductIfExist(long)} which loads the entity or
     *   throws when not present.
     */
    @Override
    public ProductResponseDTO getProductById(Long id){
        var startTime = System.currentTimeMillis();

        Product product = getProductIfExist(id);

        if(Boolean.TRUE.equals(product.getDeletedProduct())){
            throw new RuntimeException("PRODUCT IS DELETED");
        }

        Timer.measure("[GET PRODUCT BY ID] - Successfully", startTime);

        return ProductMapper.toDTO(product);
    }

    /**
     * Create a new product from the provided request DTO.
     *
     * Notes:
     * - Mapping from DTO to entity is performed by {@link ProductMapper}.
     * - The created entity id is returned so callers can reference the new resource.
     */
    @Override
    @Transactional
    public long createProduct(ProductRequestDTO dto){
        var startTime = System.currentTimeMillis();
        Product product = ProductMapper.toEntity(dto);
        Product saved = productRepository.save(product);

        Timer.measure("[CREATE PRODUCT] - Successfully", startTime);

        return saved.getId();
    }

    /**
     * Update an existing product (full update semantics).
     *
     * Behavior:
     * - Rejects updates when the product is soft-deleted.
     * - Delegates field copying to {@link #updateEntityFromDto(Product, ProductRequestDTO)}
     *   and sets the last update timestamp.
     */
    @Override
    @Transactional
    public void updateProduct(Long id, ProductRequestDTO dto){
        var startTime = System.currentTimeMillis();

        Product existing = getProductIfExist(id);

        if (Boolean.TRUE.equals(existing.getDeletedProduct())){
            throw new RuntimeException("CANNOT UPDATE A DELETED PRODUCT");
        }

        updateEntityFromDto(existing, dto);
        existing.setLastUpdate(LocalDateTime.now());

        productRepository.save(existing);

        Timer.measure("[UPDATE PRODUCT] - Successfully", startTime);

    }

    /**
     * Soft-delete a product by marking the deleted flag and updating timestamp.
     *
     * Behavior: subsequent queries should exclude soft-deleted products.
     */
    @Override
    public void deleteProduct(Long id){
        var startTime = System.currentTimeMillis();
        Product existing = getProductIfExist(id);

        if (Boolean.TRUE.equals(existing.getDeletedProduct())){
            throw new RuntimeException("PRODUCT ALREADY DELETED");
        }

        existing.setDeletedProduct(true);
        existing.setLastUpdate(LocalDateTime.now());

        productRepository.save(existing);

        Timer.measure("[DELETE PRODUCT] - Successfully", startTime);
    }

    /**
     * Apply a partial update (PATCH) to a product using {@link ProductUpdateDTO}.
     *
     * Notes:
     * - The DTO's applyTo method handles only non-null fields.
     * - The method persists the updated entity and returns the updated DTO.
     */
    @Override
    @Transactional
    public ProductResponseDTO partialUpdateProduct(Long id, ProductUpdateDTO dto){
        var startTime = System.currentTimeMillis();

        Product existing = getProductIfExist(id);

        if(Boolean.TRUE.equals(existing.getDeletedProduct())){
            throw new RuntimeException("CANNOT UPDATE A DELETED PRODUCT");
        }

        dto.applyTo(existing);

        productRepository.save(existing);

        Timer.measure("[PATCH PRODUCT] - Successfully", startTime);

        return ProductMapper.toDTO(existing);
    }


    /**
     * Helper: load a product by id or throw if absent.
     *
     * Recommendation: use {@link ProductRepository#getById(Object)} with Optional
     * handling to make 'not found' behavior explicit and avoid deprecated calls.
     */
    private Product getProductIfExist(long id) {
        Product product = productRepository.getById(id);
        if (product == null) {
            throw new RuntimeException("PRODUCT NOT FOUND");
        }

        return product;

    }

    /**
     * Copy all fields from request DTO into an existing Product entity.
     *
     * The mapper is used to convert DTO values that require parsing (e.g. enums)
     * and then fields are copied onto the existing entity before saving.
     */
    private void updateEntityFromDto(Product existing, ProductRequestDTO dto){

        Product fromDto = ProductMapper.toEntity(dto);

        existing.setName(dto.name());
        existing.setType(fromDto.getType());
        existing.setAnimalType(fromDto.getAnimalType());
        existing.setBrand(dto.brand());
        existing.setDescription(dto.description());
        existing.setStock(dto.stock());
        existing.setPrice(dto.price());
        existing.setSizeWeight(dto.sizeWeight());
        existing.setLastUpdate(LocalDateTime.now());

    }
}
