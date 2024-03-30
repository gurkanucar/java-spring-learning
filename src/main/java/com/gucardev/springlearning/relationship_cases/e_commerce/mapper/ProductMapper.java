package com.gucardev.springlearning.relationship_cases.e_commerce.mapper;

import com.gucardev.springlearning.relationship_cases.e_commerce.dto.ProductDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "categoryIds", expression = "java(product.getCategories().stream().map(Category::getId).collect(java.util.stream.Collectors.toSet()))")
    ProductDTO toDto(Product product);

    ProductDTO.MerchantDTO toMerchantDto(Merchant merchant);

    ProductDTO.CategoryDTO toCategoryDto(Category category);

    @Mapping(target = "optionType", source = "optionType")
    ProductDTO.OptionValueDTO toOptionValueDto(OptionValue optionValue);

    ProductDTO.OptionTypeDTO toOptionTypeDto(OptionType optionType);
}
