package org.gucardev.mixed.relationship_cases.e_commerce.mapper;

import org.gucardev.mixed.relationship_cases.e_commerce.dto.OrderDTO;
import org.gucardev.mixed.relationship_cases.e_commerce.dto.OrderItemDTO;
import org.gucardev.mixed.relationship_cases.e_commerce.entitiy.OptionType;
import org.gucardev.mixed.relationship_cases.e_commerce.entitiy.OptionValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OptionValueMapper {
    OrderItemDTO.OptionValueDTO toDto(OptionValue optionValue);

    OrderItemDTO.OptionTypeDTO toOptionTypeDto(OptionType optionType);
}
