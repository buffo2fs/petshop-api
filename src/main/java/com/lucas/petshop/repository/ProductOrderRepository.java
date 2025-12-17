package com.lucas.petshop.repository;

import com.lucas.petshop.model.Order;
import com.lucas.petshop.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository for the {@link ProductOrder} entity.
 *
 * <p>Extends {@link JpaRepository} to inherit standard CRUD and pagination
 * operations (save, findById, findAll, deleteById, etc.).
 *
 * Add custom query method signatures here when you need application-specific
 * queries (Spring Data will implement them by method name or via @Query).</p>
 */
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> findByOrder(Order order);


}