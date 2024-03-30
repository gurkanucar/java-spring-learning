package com.gucardev.springlearning.relationship_cases.e_commerce.mapper;

import com.gucardev.springlearning.relationship_cases.e_commerce.dto.CategoryDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "parent.id", target = "parentId")
    CategoryDTO toDto(Category category);

}
