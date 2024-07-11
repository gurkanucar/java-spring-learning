package com.gucardev.springlearning.parent_child_caching.mapper;

import com.gucardev.springlearning.parent_child_caching.dto.UserDto;
import com.gucardev.springlearning.parent_child_caching.dao.entity.Userr;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapperr {

    UserDto userToUserDto(Userr user);

}
