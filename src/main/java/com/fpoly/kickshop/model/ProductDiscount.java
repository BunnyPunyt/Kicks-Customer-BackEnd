package com.fpoly.kickshop.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Product_discount")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "discount_rate", nullable = false)
    private Double discountRate;
}

