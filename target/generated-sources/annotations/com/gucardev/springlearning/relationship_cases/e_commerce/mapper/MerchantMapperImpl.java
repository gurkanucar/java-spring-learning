package com.gucardev.springlearning.relationship_cases.e_commerce.mapper;

import com.gucardev.springlearning.relationship_cases.e_commerce.dto.MerchantDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.dto.ProductDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.Merchant;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.Product;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T21:47:10+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class MerchantMapperImpl implements MerchantMapper {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public MerchantDTO toDtoWithoutProducts(Merchant merchant) {
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
    public MerchantDTO toDto(Merchant merchant) {
        if ( merchant == null ) {
            return null;
        }

        MerchantDTO merchantDTO = new MerchantDTO();

        merchantDTO.setProducts( productSetToProductDTOSet( merchant.getProducts() ) );
        merchantDTO.setId( merchant.getId() );
        merchantDTO.setName( merchant.getName() );
        merchantDTO.setAddress( merchant.getAddress() );

        return merchantDTO;
    }

    protected Set<ProductDTO> productSetToProductDTOSet(Set<Product> set) {
        if ( set == null ) {
            return null;
        }

        Set<ProductDTO> set1 = new HashSet<ProductDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Product product : set ) {
            set1.add( productMapper.toDtoIgnoreMerchant( product ) );
        }

        return set1;
    }
}
