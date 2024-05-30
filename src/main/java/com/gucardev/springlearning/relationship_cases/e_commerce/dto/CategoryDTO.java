package com.gucardev.springlearning.relationship_cases.e_commerce.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    private Boolean isMainCategory;
    private String name;
    private Long parentId;
}
