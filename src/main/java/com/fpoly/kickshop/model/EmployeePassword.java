package com.fpoly.kickshop.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Employee_password")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "hashed_password", nullable = false, length = 255)
    private String hashedPassword;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();
}
