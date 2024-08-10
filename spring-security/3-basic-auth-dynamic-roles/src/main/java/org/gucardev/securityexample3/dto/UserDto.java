package org.gucardev.securityexample3.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private boolean isEnabled;
    private Set<RoleDto> roles;
}
