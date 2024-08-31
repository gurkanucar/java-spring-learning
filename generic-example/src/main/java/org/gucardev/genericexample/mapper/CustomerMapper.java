package org.gucardev.genericexample.mapper;

import org.gucardev.genericexample.dto.AccountDto;
import org.gucardev.genericexample.dto.CardDto;
import org.gucardev.genericexample.dto.CustomerDto;
import org.gucardev.genericexample.entity.Account;
import org.gucardev.genericexample.entity.Card;
import org.gucardev.genericexample.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CustomerMapper extends GenericMapper<Customer, CustomerDto> {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
}

