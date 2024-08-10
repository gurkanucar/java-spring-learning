package org.gucardev.securityexample2.entity;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_MOD;

    @Override
    public String getAuthority() {
        return name();
    }
}