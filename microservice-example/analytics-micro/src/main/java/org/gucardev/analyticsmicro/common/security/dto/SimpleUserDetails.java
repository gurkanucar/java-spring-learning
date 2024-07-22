package org.gucardev.analyticsmicro.common.security.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleUserDetails extends User {

    public SimpleUserDetails(String username, List<String> roles) {
        super(username, "", // Password is not used, hence empty
                true, true, true, true, // Account flags are all true
                convertRolesToAuthorities(roles));
    }

    private static Collection<? extends GrantedAuthority> convertRolesToAuthorities(List<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
