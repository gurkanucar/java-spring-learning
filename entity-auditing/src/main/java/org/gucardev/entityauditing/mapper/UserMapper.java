package org.gucardev.entityauditing.mapper;

import org.gucardev.entityauditing.dto.UserDTO;
import org.gucardev.entityauditing.dto.request.UserRequest;
import org.gucardev.entityauditing.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userRequestToUser(UserRequest userRequest);

    @Mapping(source = "addresses", target = "addressesDto", qualifiedByName = "addressToAddressDTO")
    UserDTO userToUserDTO(User user);

    @Mapping(
            source = "addresses",
            target = "addressesDto",
            qualifiedByName = "addressToAddressDTONullUserIdUsername")
    UserDTO userToUserDTONullUserIdUsername(User user);
}
