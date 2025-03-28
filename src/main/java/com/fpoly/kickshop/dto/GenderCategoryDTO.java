package com.fpoly.kickshop.dto;

import lombok.*;

@Getter
@Setter
public class GenderCategoryDTO {
    private Integer id;
    private String name;

    public GenderCategoryDTO() {
    }

    public GenderCategoryDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
