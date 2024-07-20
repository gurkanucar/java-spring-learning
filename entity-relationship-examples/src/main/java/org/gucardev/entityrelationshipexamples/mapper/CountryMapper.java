package org.gucardev.entityrelationshipexamples.mapper;

import org.gucardev.entityrelationshipexamples.dto.CountryDTO;
import org.gucardev.entityrelationshipexamples.model.Country;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    CountryDTO toDto(Country lookupValue);

    Country toEntity(CountryDTO lookupValueDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(CountryDTO dto, @MappingTarget Country entity);

    Country toEntityWithCities(CountryDTO countryDTO);

    default void linkCities(Country country) {
        if (country.getCities() != null) {
            country.getCities().forEach(x -> x.setCountry(country));
        }
    }
}
