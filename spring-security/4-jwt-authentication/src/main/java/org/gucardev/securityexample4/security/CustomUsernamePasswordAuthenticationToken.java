package org.gucardev.securityexample4.security;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class CustomUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final String token;

    public CustomUsernamePasswordAuthenticationToken(
            Object principal, Collection<? extends GrantedAuthority> authorities, String token) {
        super(principal, null, authorities);
        this.token = token;
    }
}
