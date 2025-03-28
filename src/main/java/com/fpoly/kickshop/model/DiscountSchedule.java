package com.fpoly.kickshop.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Discount_schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_discount_id", nullable = false)
    private ProductDiscount productDiscount;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;
}