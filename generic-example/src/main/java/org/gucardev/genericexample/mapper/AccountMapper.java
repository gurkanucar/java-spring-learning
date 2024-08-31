package org.gucardev.genericexample.mapper;

import org.gucardev.genericexample.dto.AccountDto;
import org.gucardev.genericexample.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper extends GenericMapper<Account, AccountDto> {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
}
