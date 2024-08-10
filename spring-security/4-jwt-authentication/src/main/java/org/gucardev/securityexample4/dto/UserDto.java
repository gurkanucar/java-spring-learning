package org.gucardev.securityexample4.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.gucardev.securityexample4.model.Role;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@JsonInclude(NON_NULL)
public class UserDto extends BaseDto {
    private String username;
    private String email;
    private Role role;
    private String token;
    private boolean isEnabled;
}
