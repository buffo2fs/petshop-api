package com.lucas.petshop.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * JPA entity representing the association between a Product and an Order.
 *
 * <p>Each instance represents one product line inside an order (a product-order entry)
 * containing the product id, the order id, quantity and unit price. Timestamps and a
 * soft-delete flag are provided to support auditing and logical deletion.</p>
 */
@Data
@Entity
@Table(name="tb_product_order")
public class ProductOrder {


    // Primary key for the association (auto-generated)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_order_id", nullable = false)
    private Long id;

    // Reference to the product (foreign key to products table)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Reference to the order that contains this product (foreign key to orders table)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Quantity of this product in the order
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    // Unit price applied for this product in the order (snapshot of price at order time)
    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    // Timestamp when this ProductOrder was created (auto-populated by Hibernate)
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Timestamp of the last update to this ProductOrder (auto-updated by Hibernate)
    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    // Soft-delete flag: when true, this association should be ignored in normal queries
    @Column(name = "deleted_product_order", nullable = false)
    private Boolean deletedProductOrder = false;

}
