package org.gucardev.springshell.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.shell.Availability;
import org.springframework.shell.AvailabilityProvider;

import java.util.List;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class SpringSecurityConfig {

  @Bean
  public AvailabilityProvider userLoggedInProvider() {
    return () ->
        (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
            ? Availability.available()
            : Availability.unavailable("You are not logged in.");
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService users() {
    UserDetails admin =
        User.builder()
            .username("admin")
            .password("pass")
            .password(passwordEncoder().encode("pass"))
            .roles("USER")
            .build();
    return new InMemoryUserDetailsManager(admin);
  }

  @Bean
  public AuthenticationManager authenticationManager(
      UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder);
    return new ProviderManager(List.of(authenticationProvider));
  }
}
