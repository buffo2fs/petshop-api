package com.lucas.petshop.model;

import com.lucas.petshop.service.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * JPA entity that represents a customer order in the system.
 *
 * <p>This entity stores the basic order metadata (item counts, client identifiers,
 * total amount, status and timestamps). It uses JPA annotations for persistence
 * mapping and Hibernate annotations to auto-populate creation/update timestamps.
 * Lombok's {@code @Data} generates boilerplate getters/setters/toString/hashCode
 * so the class remains concise.</p>
 */
@Data
@Entity
@Table(name="tb_orders")
public class Order {

    // Primary key: database-generated identifier for the order
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Sum of items contained in the order (aggregate quantity)
    @Column(name = "total_items_count", nullable = false)
    private Integer totalItemsCount;

    // Name of the client who placed the order (person or company)
    @Column(name = "client", nullable = false)
    private String client;

    // Monetary total for the entire order (currency handling should be done at a higher level)
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    // Order lifecycle status (uses an enum stored as string in the DB)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatusEnum status;

    // Timestamp when the order record was created (auto-managed by Hibernate)
    @CreationTimestamp
    @Column(name = "order_creation", nullable = false)
    private LocalDateTime orderCreation;

    // Timestamp of the last update to the order record (auto-managed by Hibernate)
    @UpdateTimestamp
    @Column(name = "order_update")
    private LocalDateTime orderUpdate;

    // Soft-delete flag: when true the order is considered deleted/archived
    @Column(name = "deleted_order", nullable = false)
    private Boolean deletedOrder = false;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<ProductOrder> productOrders;

}
