package com.fpoly.kickshop.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Size_Sample")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SizeSample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String size;
}
