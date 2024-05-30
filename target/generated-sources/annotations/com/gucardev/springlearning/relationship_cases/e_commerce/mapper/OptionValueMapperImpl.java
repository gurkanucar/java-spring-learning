package com.gucardev.springlearning.relationship_cases.e_commerce.mapper;

import com.gucardev.springlearning.relationship_cases.e_commerce.dto.OrderItemDTO.OptionTypeDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.dto.OrderItemDTO.OptionValueDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.OptionType;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.OptionValue;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T21:47:10+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class OptionValueMapperImpl implements OptionValueMapper {

    @Override
    public OptionValueDTO toDto(OptionValue optionValue) {
        if ( optionValue == null ) {
            return null;
        }

        OptionValueDTO optionValueDTO = new OptionValueDTO();

        optionValueDTO.setId( optionValue.getId() );
        optionValueDTO.setValue( optionValue.getValue() );
        optionValueDTO.setOptionType( toOptionTypeDto( optionValue.getOptionType() ) );

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
}
