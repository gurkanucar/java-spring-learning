package org.gucardev.genericexample.mapper;

import org.gucardev.genericexample.dto.CardDto;
import org.gucardev.genericexample.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CardMapper extends GenericMapper<Card, CardDto> {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);
}
