package com.fpoly.kickshop.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DiscountDTO {
    private Integer id;
    private Double discountRate;
    private LocalDate startDate;
    private LocalDate endDate;
}
