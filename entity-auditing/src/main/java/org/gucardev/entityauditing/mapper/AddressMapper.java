package org.gucardev.entityauditing.mapper;

import org.gucardev.entityauditing.dto.AddressDTO;
import org.gucardev.entityauditing.dto.request.AddressRequest;
import org.gucardev.entityauditing.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface AddressMapper {
  AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

  @Mapping(source = "userId", target = "user.id")
  Address addressRequestToAddress(AddressRequest addressRequest);

  @Named("addressToAddressDTO")
  @Mapping(source = "user.id", target = "userId")
  @Mapping(source = "user.username", target = "username")
  AddressDTO addressToAddressDTO(Address address);

  @Named("addressToAddressDTONullUserIdUsername")
  @Mapping(target = "userId", ignore = true)
  @Mapping(target = "username", ignore = true)
  AddressDTO addressToAddressDTONullUserIdUsername(Address address);
}
