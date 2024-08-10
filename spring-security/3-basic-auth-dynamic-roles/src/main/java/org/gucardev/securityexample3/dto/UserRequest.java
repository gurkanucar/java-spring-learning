package org.gucardev.securityexample3.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String username;
    private String password;
    private Boolean enabled;
    private Set<RoleDto> roles;
}
