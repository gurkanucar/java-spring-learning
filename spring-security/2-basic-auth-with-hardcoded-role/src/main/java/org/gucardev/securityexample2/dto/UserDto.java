package org.gucardev.securityexample2.dto;

import lombok.Getter;
import lombok.Setter;
import org.gucardev.securityexample2.entity.Role;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private boolean isEnabled;
    private Role role;
}
