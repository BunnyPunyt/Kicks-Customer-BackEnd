package com.fpoly.kickshop.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Gallery")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_detail_id", nullable = false)
    private ProductDetail productDetail;

    private String image;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;
}
