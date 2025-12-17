package com.lucas.petshop.model;

import com.lucas.petshop.service.RatingStarsEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="tb_rating")
public class Rating {

    @Id
    @Column(name = "rating_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(name = "stars", nullable = false)
    private RatingStarsEnum stars;

    @Column(name = "client", nullable = false)
    private String client;

    @Column(name = "comments")
    private String comments;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @Column(name = "deleted_rating", nullable = false)
    private Boolean deletedRating = false;

}
