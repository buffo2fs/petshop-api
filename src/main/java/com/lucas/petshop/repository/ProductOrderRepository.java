package com.lucas.petshop.repository;

import com.lucas.petshop.model.ProductOrder;
import com.lucas.petshop.model.ProductOrderKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

    @Modifying // Necessário para operações de DELETE ou UPDATE
    @Query("DELETE FROM ProductOrder po WHERE po.order.id = :orderId")
    void deleteByOrderId(@Param("orderId") Long orderId);
}
