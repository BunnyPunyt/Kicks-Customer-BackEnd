package com.fpoly.kickshop.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private Integer id;
    private String name;
    private Double price;
    private String brand;
    private String description;
    private String category;
    private String genderCategory;
    private String supplier;
    private List<ProductDetailDTO> productDetails;
}
