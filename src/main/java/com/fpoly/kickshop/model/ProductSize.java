package com.fpoly.kickshop.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Product_size")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_detail_id", nullable = false)
    private ProductDetail productDetail;

    private String size;

    private Integer stock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
