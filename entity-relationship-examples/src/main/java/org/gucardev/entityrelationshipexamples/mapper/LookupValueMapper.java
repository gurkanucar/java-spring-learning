package org.gucardev.entityrelationshipexamples.mapper;

import org.gucardev.entityrelationshipexamples.dto.LookupValueDTO;
import org.gucardev.entityrelationshipexamples.model.LookupValue;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LookupValueMapper {
    LookupValueMapper INSTANCE = Mappers.getMapper(LookupValueMapper.class);

    LookupValueDTO toDto(LookupValue lookupValue);

    LookupValue toEntity(LookupValueDTO lookupValueDTO);
}
