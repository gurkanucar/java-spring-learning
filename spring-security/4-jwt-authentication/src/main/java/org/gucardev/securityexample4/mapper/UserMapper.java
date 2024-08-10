package org.gucardev.securityexample4.mapper;

import org.gucardev.securityexample4.dto.UserDto;
import org.gucardev.securityexample4.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper extends MapperUtil {

    UserDto toDto(User entity);

    @Mapping(target = "createdDateTime", ignore = true)
    @Mapping(target = "updatedDateTime", ignore = true)
//    @Mapping(target = "user.id", expression = "java(getCurrentUserId())")
    User toEntity(UserDto dto);

    @Mapping(target = "createdDateTime", ignore = true)
    @Mapping(target = "updatedDateTime", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDto dto, @MappingTarget User entity);
}
