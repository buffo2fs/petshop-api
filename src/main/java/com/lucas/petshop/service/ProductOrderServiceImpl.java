package com.lucas.petshop.service;

import com.lucas.petshop.dto.OrderRequestDTO;
import com.lucas.petshop.dto.ProductOrderRequestDTO;
import com.lucas.petshop.dto.ProductOrderResponseDTO;
import com.lucas.petshop.dto.ProductOrderUpdateDTO;
import com.lucas.petshop.mapper.ProductOrderMapper;
import com.lucas.petshop.model.ProductOrder;
import com.lucas.petshop.repository.ProductOrderRepository;
import com.lucas.petshop.util.Timer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing ProductOrder entries (product lines inside orders).
 *
 * <p>This class provides CRUD-like operations for the ProductOrder entity. It
 * enforces soft-delete semantics (a boolean flag) and centralizes mapping to/from
 * DTOs via {@link ProductOrderMapper}. Methods use the repository for persistence
 * and the {@link Timer} utility for simple timing/logging.</p>
 */
@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    // Repository used to persist and load ProductOrder entities. Injected by Spring.
    @Autowired
    private ProductOrderRepository productOrderRepository;

    /**
     * Return a list of all product-order DTOs that are not soft-deleted.
     *
     * Behavior:
     * - Filters out entries where deletedProductOrder == true so downstream
     *   callers only see active associations.
     * - Uses the mapper to convert entities to response DTOs.
     */
    @Override
    public List<ProductOrderResponseDTO> getAllProductsOrders(){
        var startTime = System.currentTimeMillis();
        Timer.measure("[GET ALL PRODUCTS ORDERS] - Successfully", startTime);

        return productOrderRepository.findAll()
                .stream()
                .filter(productOrder -> !Boolean.TRUE.equals(productOrder.getDeletedProductOrder()))
                .map(ProductOrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve a single product-order entry by id and return its DTO.
     *
     * Notes:
     * - Throws a RuntimeException if the entry is soft-deleted; consider mapping
     *   this to a 404 or 410 at the controller/advice layer.
     */
    @Override
    public ProductOrderResponseDTO getProductsOrdersById(Long id){
        var startTime = System.currentTimeMillis();

        ProductOrder productOrder = getProductOrderIfExists(id);

        if (Boolean.TRUE.equals(productOrder.getDeletedProductOrder())){
            // Business-level guard: do not reveal or act on deleted entries
            throw new RuntimeException("PRODUCT ORDER IS DELETED");
        }

        Timer.measure("[GET PRODUCT ORDER BY ID] - Successfully", startTime);

        return ProductOrderMapper.toDTO(productOrder);
    }

    /**
     * Create a new ProductOrder from the provided request DTO.
     *
     * Behavior:
     * - Mapping of request data to entity is handled by the mapper.
     * - The created entity id is returned so callers can reference the new resource.
     */
    @Override
    @Transactional
    public long createProductOrder(ProductOrderRequestDTO dto){
        var startTime = System.currentTimeMillis();

        ProductOrder productOrder = ProductOrderMapper.toEntity(dto);
        ProductOrder saved = productOrderRepository.save(productOrder);

        Timer.measure("[CREATE PRODUCT ORDER] - Successfully", startTime);

        return saved.getId();
    }

    /**
     * Update an existing product-order entry with values from the update DTO.
     *
     * Notes:
     * - The method rejects updates to entries already marked as deleted.
     * - After applying changes, it updates the lastUpdate timestamp before saving.
     */
    @Override
    @Transactional
    public void updateProductOrder(Long id, ProductOrderUpdateDTO dto){
        var startTime = System.currentTimeMillis();

        ProductOrder existing = getProductOrderIfExists(id);

        if(Boolean.TRUE.equals(existing.getDeletedProductOrder())){
            throw new RuntimeException("CANNOT UPDATE A DELETED PRODUCT ORDER");
        }

        updateEntityFromDto(existing, dto);
        existing.setLastUpdate(LocalDateTime.now());

        productOrderRepository.save(existing);

        Timer.measure("[UPDATE PRODUCT ORDER] - Successfully", startTime);

    }

    /**
     * Soft-delete a product-order entry by setting the deleted flag and updating timestamp.
     */
    @Override
    @Transactional
    public void deleteProductOrder(Long id){
        var startTime = System.currentTimeMillis();
        ProductOrder existing = getProductOrderIfExists(id);

        if(Boolean.TRUE.equals(existing.getDeletedProductOrder())){
            throw new RuntimeException("PRODUCT ORDER ALREADY DELETED");
        }

        existing.setDeletedProductOrder(true);
        existing.setLastUpdate(LocalDateTime.now());

        productOrderRepository.save(existing);

        Timer.measure("[DELETE PRODUCT ORDER] - Successfully", startTime);

    }

    /**
     * Helper: load a ProductOrder by id or throw if absent.
     *
     * Note: prefer using repository.findById(...) with Optional handling when you
     * want clear semantics for the 'not found' case; getById may return a proxy
     * or be deprecated in some JPA implementations.
     */
    private ProductOrder getProductOrderIfExists(long id) {
        ProductOrder productOrder = productOrderRepository.getById(id);
        if (productOrder == null) {
            throw new RuntimeException("Order not found");
        }

        return productOrder;
    }

    /**
     * Apply fields from the update DTO to the existing entity instance.
     *
     * This centralizes update mapping logic so callers don't duplicate field copying.
     */
    private void updateEntityFromDto(ProductOrder existing, ProductOrderUpdateDTO dto){

        existing.setProductId(dto.productId());
        existing.setOrderId(dto.orderId());
        existing.setQuantity(dto.quantity());
        existing.setUnitPrice(dto.unitPrice());
    }

}
