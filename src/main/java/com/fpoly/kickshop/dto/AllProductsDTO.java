package com.fpoly.kickshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllProductsDTO {
    private Integer productId;
    private String name;
    private Double price;
    private String brand;
    private String description;

    private Integer productDetailId;
    private String color;
    private Boolean isDefaultDetail;

    private Integer productSizeId;
    private String size;
    private Integer stock;

    private Integer galleryId;
    private String galleryImage;
    private Boolean isDefaultImage;

    private Integer genderCategoryId;
    private String genderCategoryName;

    private Integer shoeCategoryId;
    private String shoeCategoryName;

    public String getShoeCategoryName() {
        return shoeCategoryName;
    }

    public void setShoeCategoryName(String shoeCategoryName) {
        this.shoeCategoryName = shoeCategoryName;
    }

    public Integer getShoeCategoryId() {
        return shoeCategoryId;
    }

    public void setShoeCategoryId(Integer shoeCategoryId) {
        this.shoeCategoryId = shoeCategoryId;
    }

    public String getGenderCategoryName() {
        return genderCategoryName;
    }

    public void setGenderCategoryName(String genderCategoryName) {
        this.genderCategoryName = genderCategoryName;
    }

    public Integer getGenderCategoryId() {
        return genderCategoryId;
    }

    public void setGenderCategoryId(Integer genderCategoryId) {
        this.genderCategoryId = genderCategoryId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public Integer getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(Integer productDetailId) {
        this.productDetailId = productDetailId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getIsDefaultDetail() {
        return isDefaultDetail;
    }

    public void setIsDefaultDetail(Boolean isDefaultDetail) {
        this.isDefaultDetail = isDefaultDetail;
    }

    public Integer getProductSizeId() {
        return productSizeId;
    }

    public void setProductSizeId(Integer productSizeId) {
        this.productSizeId = productSizeId;
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

    public Integer getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(Integer galleryId) {
        this.galleryId = galleryId;
    }

    public String getGalleryImage() {
        return galleryImage;
    }

    public void setGalleryImage(String galleryImage) {
        this.galleryImage = galleryImage;
    }

    public Boolean getIsDefaultImage() {
        return isDefaultImage;
    }

    public void setIsDefaultImage(Boolean isDefaultImage) {
        this.isDefaultImage = isDefaultImage;
    }


}
