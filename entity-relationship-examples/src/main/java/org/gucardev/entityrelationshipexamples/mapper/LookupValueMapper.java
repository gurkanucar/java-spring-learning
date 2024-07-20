package org.gucardev.entityrelationshipexamples.mapper;

import org.gucardev.entityrelationshipexamples.dto.LookupCategoryDTO;
import org.gucardev.entityrelationshipexamples.dto.LookupValueDTO;
import org.gucardev.entityrelationshipexamples.model.LookupCategory;
import org.gucardev.entityrelationshipexamples.model.LookupValue;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;

@Mapper(componentModel = "spring")
public interface LookupValueMapper {
    LookupValueMapper INSTANCE = Mappers.getMapper(LookupValueMapper.class);

    LookupValueDTO toDto(LookupValue entity);

    @Mapping(source = "categoryId", target = "category.id")
    LookupValue toEntity(LookupValueDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "translations", ignore = true)
    void updateFromDto(LookupValueDTO dto, @MappingTarget LookupValue entity);

    default void updateTranslationsMap(LookupValueDTO dto, @MappingTarget LookupValue entity) {
        if (dto.getTranslations() == null || dto.getTranslations().isEmpty()) {
            entity.setTranslations(new HashMap<>());
        } else {
            entity.setTranslations(new HashMap<>(dto.getTranslations()));
        }
    }

}
