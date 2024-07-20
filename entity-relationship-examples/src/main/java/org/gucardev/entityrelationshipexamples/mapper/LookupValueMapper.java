package org.gucardev.entityrelationshipexamples.mapper;

import org.gucardev.entityrelationshipexamples.dto.LookupValueDTO;
import org.gucardev.entityrelationshipexamples.model.LookupValue;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LookupValueMapper {
    LookupValueMapper INSTANCE = Mappers.getMapper(LookupValueMapper.class);

    LookupValueDTO toDto(LookupValue lookupValue);

    @Mapping(source = "categoryId", target = "category.id")
    LookupValue toEntity(LookupValueDTO lookupValueDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category", ignore = true)
    void updateFromDto(LookupValueDTO dto, @MappingTarget LookupValue entity);

}
