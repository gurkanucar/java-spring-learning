package org.gucardev.genericexample.mapper;

import org.gucardev.genericexample.dto.CardDto;
import org.gucardev.genericexample.entity.Card;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CardMapper extends GenericMapper<Card, CardDto> {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    @Override
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "account.id", source = "accountId")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Card partialUpdate(CardDto dto, @MappingTarget Card entity);
}
