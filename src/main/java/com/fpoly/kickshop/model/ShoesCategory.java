package com.fpoly.kickshop.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Shoes_Category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoesCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_category_id", nullable = false)
    private GenderCategory genderCategory;

    @Column(nullable = false, length = 100)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenderCategory getGenderCategory() {
        return genderCategory;
    }

    public void setGenderCategory(GenderCategory genderCategory) {
        this.genderCategory = genderCategory;
    }
}
