package org.gucardev.genericexample.mapper;

import org.gucardev.genericexample.dto.AccountDto;
import org.gucardev.genericexample.entity.Account;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper extends GenericMapper<Account, AccountDto> {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Override
    @Mapping(target = "cards",ignore = true)
    AccountDto toDto(Account entity);

    AccountDto toDtoWithCards(Account entity);

    @Override
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Account partialUpdate(AccountDto dto, @MappingTarget Account entity);
}
