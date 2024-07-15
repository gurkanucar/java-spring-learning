package org.gucardev.mixed.relationship_cases.lookup;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LookupMapper {

    LookupMapper INSTANCE = Mappers.getMapper(LookupMapper.class);

    @Mapping(target = "id", ignore = true)
    Lookup toEntity(LookupDto dto);

    LookupDto toDto(Lookup entity);

    default void updateFromDtoWithNullCheck(LookupDto dto, @MappingTarget Lookup entity) {
        if (dto.getPayloads() == null || dto.getPayloads().isEmpty()) {
            entity.setPayloads(null);
        } else {
            // set other fields too
            entity.setPayloads(dto.getPayloads());
        }
    }
}
