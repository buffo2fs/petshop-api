package com.lucas.petshop.repository;

import com.lucas.petshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Order} entity.
 *
 * <p>Extends {@link JpaRepository} to inherit standard CRUD and pagination
 * operations (save, findById, findAll, deleteById, etc.).
 *
 * Add custom query method signatures here if you need application-specific
 * queries (Spring Data will implement them by method name or via @Query).</p>
 */
public interface OrderRepository extends JpaRepository<Order, Long>{
}