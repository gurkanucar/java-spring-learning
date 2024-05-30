package com.gucardev.springlearning.relationship_cases.e_commerce.mapper;

import com.gucardev.springlearning.relationship_cases.e_commerce.dto.CategoryDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T21:47:10+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setParentId( categoryParentId( category ) );
        categoryDTO.setId( category.getId() );
        categoryDTO.setIsMainCategory( category.getIsMainCategory() );
        categoryDTO.setName( category.getName() );

        return categoryDTO;
    }

    private Long categoryParentId(Category category) {
        if ( category == null ) {
            return null;
        }
        Category parent = category.getParent();
        if ( parent == null ) {
            return null;
        }
        Long id = parent.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
