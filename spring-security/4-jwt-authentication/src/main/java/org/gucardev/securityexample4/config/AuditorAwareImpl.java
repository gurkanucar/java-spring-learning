package org.gucardev.securityexample4.config;

import lombok.extern.slf4j.Slf4j;
import org.gucardev.securityexample4.constants.Constants;
import org.gucardev.securityexample4.security.CustomUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == "anonymousUser") {
            return Optional.of(Constants.DEFAULT_AUDITOR);
        }
        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
        try {
            return Optional.of(userPrincipal.getUsername().toString());
        } catch (IllegalArgumentException e) {
            log.error("Could not get current auditor", e);
            return Optional.of(Constants.DEFAULT_AUDITOR);
        }
    }
}
