package org.gucardev.securityexample2.dto;

import lombok.Getter;
import lombok.Setter;
import org.gucardev.securityexample2.entity.Role;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String username;
    private String password;
    private Boolean enabled;
    private Role role;
}
