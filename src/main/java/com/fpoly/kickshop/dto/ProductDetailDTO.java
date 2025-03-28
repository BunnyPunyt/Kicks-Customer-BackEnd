package com.fpoly.kickshop.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDetailDTO {
    private Integer id;
    private String color;
    private boolean isDefault;
    private List<ProductSizeDTO> sizes;
    private List<GalleryDTO> images;
    private DiscountDTO discount;
}
