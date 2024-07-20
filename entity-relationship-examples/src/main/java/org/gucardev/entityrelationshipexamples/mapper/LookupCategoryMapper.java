package org.gucardev.entityrelationshipexamples.mapper;

import org.gucardev.entityrelationshipexamples.dto.LookupCategoryDTO;
import org.gucardev.entityrelationshipexamples.model.LookupCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LookupCategoryMapper {
    LookupCategoryMapper INSTANCE = Mappers.getMapper(LookupCategoryMapper.class);

    LookupCategoryDTO toDto(LookupCategory lookupCategory);
    LookupCategory toEntity(LookupCategoryDTO lookupCategoryDTO);
}
