package com.fpoly.kickshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fpoly.kickshop.model.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetailDTO {

    private Integer productId;
    private Double price;
    private String size;
    private String color;
    private Integer quantity;
    private String productName;

    @JsonProperty("image")
    private String image;

    public OrderDetailDTO(OrderDetail orderDetail) {
        this.productId = orderDetail.getProduct().getId();
        this.price = orderDetail.getPrice();
        this.size = orderDetail.getSize();
        this.color = orderDetail.getColor();
        this.quantity = orderDetail.getQuantity();
        this.image = orderDetail.getImage();
        this.productName = orderDetail.getProduct().getName();
    }

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(Integer productId, Double price, String size, String color, Integer quantity, String image) {
        this.productId = productId;
        this.price = price;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
