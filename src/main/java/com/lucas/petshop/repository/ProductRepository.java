package com.lucas.petshop.repository;

import com.lucas.petshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the {@link Product} entity.
 *
 * <p>Provides standard CRUD operations via {@link JpaRepository} and exposes
 * a couple of convenience query methods used by the application to filter
 * out logically-deleted records.</p>
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Find all products that are not marked as deleted.
     *
     * This convenience method relies on Spring Data JPA's query derivation
     * by method name. It maps to a query that filters where `deletedProduct` is false.
     */
    List<Product> findByDeletedProductFalse();

    /**
     * Find a product by id only if it is not marked as deleted.
     *
     * Returns an Optional which will be empty when the product does not exist
     * or has been marked deleted. Use this from service layer to implement
     * soft-delete semantics.
     */
    Optional<Product> findByIdAndDeletedProductFalse(Long id);
}
