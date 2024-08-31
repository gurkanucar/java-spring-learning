package org.gucardev.genericexample.mapper;

import org.gucardev.genericexample.dto.CustomerDto;
import org.gucardev.genericexample.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CustomerMapper extends GenericMapper<Customer, CustomerDto> {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
}

