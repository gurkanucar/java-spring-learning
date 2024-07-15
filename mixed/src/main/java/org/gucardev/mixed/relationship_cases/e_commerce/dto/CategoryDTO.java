package org.gucardev.mixed.relationship_cases.e_commerce.dto;

import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    private boolean main;
    private String name;
    private Long parentId;
}
