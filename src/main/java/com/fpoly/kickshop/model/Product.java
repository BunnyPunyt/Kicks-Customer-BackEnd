package com.fpoly.kickshop.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoes_category_id", nullable = false)
    private ShoesCategory shoesCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_category_id", nullable = false)
    private GenderCategory genderCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false)
    private Double price;

    private String brand;

    private String description;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ShoesCategory getShoesCategory() {
        return shoesCategory;
    }

    public void setShoesCategory(ShoesCategory shoesCategory) {
        this.shoesCategory = shoesCategory;
    }

    public GenderCategory getGenderCategory() {
        return genderCategory;
    }

    public void setGenderCategory(GenderCategory genderCategory) {
        this.genderCategory = genderCategory;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}

