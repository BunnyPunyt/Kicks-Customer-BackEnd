package com.fpoly.kickshop.dto;

import lombok.Data;

@Data
public class GalleryDTO {
    private Integer id;
    private String imageUrl;
    private boolean isDefault;
}
