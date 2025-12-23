package com.lucas.petshop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_products_orders")
@Data
public class ProductOrder {

    @EmbeddedId
    private ProductOrderKey id = new ProductOrderKey();

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer quantity;
    private BigDecimal unitPrice;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
