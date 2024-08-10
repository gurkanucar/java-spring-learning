package org.gucardev.securityexample3.mapper;

import org.gucardev.securityexample3.dto.UserDto;
import org.gucardev.securityexample3.dto.UserRequest;
import org.gucardev.securityexample3.entity.User;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto toDto(User user) {
        var dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEnabled(user.getIsEnabled());
        dto.setName(user.getName());
        dto.setRoles(user.getRoles().stream().map(RoleMapper::toDto).collect(Collectors.toSet()));
        return dto;
    }

    public static User toEntity(UserRequest request) {
        var entity = new User();
        entity.setIsEnabled(true);
        entity.setRoles(request.getRoles().stream().map(RoleMapper::toEntity).collect(Collectors.toSet()));
        entity.setName(request.getName());
        entity.setPassword(request.getPassword());
        entity.setUsername(request.getUsername());
        return entity;
    }

    public static void update(User target, UserDto source) {
        target.setRoles(source.getRoles().stream().map(RoleMapper::toEntity).collect(Collectors.toSet()));
        target.setName(source.getName());
        target.setUsername(source.getUsername());
    }
}
