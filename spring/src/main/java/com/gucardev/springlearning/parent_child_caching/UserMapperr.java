package com.gucardev.springlearning.parent_child_caching;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface UserMapperr {

    UserDto userToUserDto(Userr user);

}
