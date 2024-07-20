package org.gucardev.entityrelationshipexamples.mapper;

import org.gucardev.entityrelationshipexamples.dto.CityDTO;
import org.gucardev.entityrelationshipexamples.model.City;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    CityDTO toDto(City entity);

    @Mapping(source = "countryId", target = "country.id")
    City toEntity(CityDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "country", ignore = true)
    void updateFromDto(CityDTO dto, @MappingTarget City entity);

}
