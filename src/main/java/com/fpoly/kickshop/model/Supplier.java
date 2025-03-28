package com.fpoly.kickshop.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Supplier")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String name;

    private String address;

    @Column(name = "contact_info", length = 100)
    private String contactInfo;
}
