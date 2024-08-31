package org.gucardev.genericexample.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface GenericMapper<E, D> {

    D toDto(E entity);

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    E toEntity(D dto);

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    E partialUpdate(D dto, @MappingTarget E entity);
}
