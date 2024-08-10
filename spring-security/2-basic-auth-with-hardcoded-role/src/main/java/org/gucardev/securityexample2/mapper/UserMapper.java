package org.gucardev.securityexample2.mapper;

import org.gucardev.securityexample2.dto.UserDto;
import org.gucardev.securityexample2.dto.UserRequest;
import org.gucardev.securityexample2.entity.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        var dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEnabled(user.getIsEnabled());
        dto.setName(user.getName());
        dto.setRole(user.getRole());
        return dto;
    }

    public static User toEntity(UserRequest request) {
        var entity = new User();
        entity.setIsEnabled(true);
        entity.setRole(request.getRole());
        entity.setName(request.getName());
        entity.setPassword(request.getPassword());
        entity.setUsername(request.getUsername());
        return entity;
    }

    public static void update(User target, UserDto source) {
        target.setRole(source.getRole());
        target.setName(source.getName());
        target.setUsername(source.getUsername());
    }
}
