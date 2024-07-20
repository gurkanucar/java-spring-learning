package org.gucardev.entityrelationshipexamples.mapper;

import org.gucardev.entityrelationshipexamples.dto.LookupCategoryDTO;
import org.gucardev.entityrelationshipexamples.model.LookupCategory;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;

@Mapper(componentModel = "spring")
public interface LookupCategoryMapper {
    LookupCategoryMapper INSTANCE = Mappers.getMapper(LookupCategoryMapper.class);

    LookupCategoryDTO toDto(LookupCategory lookupCategory);

    LookupCategory toEntity(LookupCategoryDTO lookupCategoryDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "lookupValues", ignore = true)
    @Mapping(target = "translations", ignore = true)
    void updateFromDto(LookupCategoryDTO dto, @MappingTarget LookupCategory entity);

    default void linkLookupValues(LookupCategory entity) {
        if (entity.getLookupValues() != null) {
            entity.getLookupValues().forEach(x -> x.setCategory(entity));
        }
    }

    default void updateTranslationsMap(LookupCategoryDTO dto, @MappingTarget LookupCategory entity) {
        if (dto.getTranslations() == null || dto.getTranslations().isEmpty()) {
            entity.setTranslations(new HashMap<>());
        } else {
            entity.setTranslations(new HashMap<>(dto.getTranslations()));
        }
    }
}
