package org.gucardev.securityexample3.mapper;

import org.gucardev.securityexample3.dto.RoleDto;
import org.gucardev.securityexample3.entity.Role;

public class RoleMapper {

    public static RoleDto toDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }

    public static Role toEntity(RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;
    }

    public static void update(Role target, RoleDto source) {
        target.setName(source.getName());
    }
}
