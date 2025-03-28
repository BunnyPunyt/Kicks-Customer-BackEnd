package com.fpoly.kickshop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class ProductSizeDTO {
    private Integer id;
    private Integer detailId;
    private String size;
    private Integer stock;

    public ProductSizeDTO(Integer id, Integer detailId, String size, Integer stock) {
        this.id = id;
        this.detailId = detailId;
        this.size = size;
        this.stock = stock;
    }

}
