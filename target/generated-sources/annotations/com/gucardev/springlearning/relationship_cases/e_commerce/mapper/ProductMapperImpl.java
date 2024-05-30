package com.gucardev.springlearning.relationship_cases.e_commerce.mapper;

import com.gucardev.springlearning.relationship_cases.e_commerce.dto.ProductDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.dto.ProductDTO.CategoryDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.dto.ProductDTO.MerchantDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.dto.ProductDTO.OptionTypeDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.dto.ProductDTO.OptionValueDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.Category;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.Merchant;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.OptionType;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.OptionValue;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.Product;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T21:47:10+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setStock( product.getStock() );
        productDTO.setSku( product.getSku() );
        productDTO.setMerchant( toMerchantDto( product.getMerchant() ) );
        productDTO.setCategories( categorySetToCategoryDTOSet( product.getCategories() ) );
        productDTO.setOptionValues( optionValueSetToOptionValueDTOSet( product.getOptionValues() ) );

        productDTO.setCategoryIds( product.getCategories().stream().map(Category::getId).collect(java.util.stream.Collectors.toSet()) );

        return productDTO;
    }

    @Override
    public ProductDTO toDtoIgnoreMerchant(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setStock( product.getStock() );
        productDTO.setSku( product.getSku() );
        productDTO.setCategories( categorySetToCategoryDTOSet( product.getCategories() ) );
        productDTO.setOptionValues( optionValueSetToOptionValueDTOSet( product.getOptionValues() ) );

        productDTO.setCategoryIds( product.getCategories().stream().map(Category::getId).collect(java.util.stream.Collectors.toSet()) );

        return productDTO;
    }

    @Override
    public MerchantDTO toMerchantDto(Merchant merchant) {
        if ( merchant == null ) {
            return null;
        }

        MerchantDTO merchantDTO = new MerchantDTO();

        merchantDTO.setId( merchant.getId() );
        merchantDTO.setName( merchant.getName() );
        merchantDTO.setAddress( merchant.getAddress() );

        return merchantDTO;
    }

    @Override
    public CategoryDTO toCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );

        return categoryDTO;
    }

    @Override
    public OptionValueDTO toOptionValueDto(OptionValue optionValue) {
        if ( optionValue == null ) {
            return null;
        }

        OptionValueDTO optionValueDTO = new OptionValueDTO();

        optionValueDTO.setOptionType( toOptionTypeDto( optionValue.getOptionType() ) );
        optionValueDTO.setId( optionValue.getId() );
        optionValueDTO.setValue( optionValue.getValue() );

        return optionValueDTO;
    }

    @Override
    public OptionTypeDTO toOptionTypeDto(OptionType optionType) {
        if ( optionType == null ) {
            return null;
        }

        OptionTypeDTO optionTypeDTO = new OptionTypeDTO();

        optionTypeDTO.setId( optionType.getId() );
        optionTypeDTO.setName( optionType.getName() );

        return optionTypeDTO;
    }

    protected Set<CategoryDTO> categorySetToCategoryDTOSet(Set<Category> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoryDTO> set1 = new HashSet<CategoryDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Category category : set ) {
            set1.add( toCategoryDto( category ) );
        }

        return set1;
    }

    protected Set<OptionValueDTO> optionValueSetToOptionValueDTOSet(Set<OptionValue> set) {
        if ( set == null ) {
            return null;
        }

        Set<OptionValueDTO> set1 = new HashSet<OptionValueDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( OptionValue optionValue : set ) {
            set1.add( toOptionValueDto( optionValue ) );
        }

        return set1;
    }
}
