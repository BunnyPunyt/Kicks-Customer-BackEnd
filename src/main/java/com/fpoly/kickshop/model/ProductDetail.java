package com.fpoly.kickshop.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Product_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_discount_id")
    private ProductDiscount productDiscount;

    private String color;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;
}

