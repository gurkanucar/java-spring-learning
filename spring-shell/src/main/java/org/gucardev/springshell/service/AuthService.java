package org.gucardev.springshell.service;

import org.gucardev.springshell.util.ShellPrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final ShellPrinter printer;

  public void login(String username, String password) {
    Authentication request = new UsernamePasswordAuthenticationToken(username, password);
    try {
      Authentication result = authenticationManager.authenticate(request);
      SecurityContextHolder.getContext().setAuthentication(result);
      printer.printSuccess("Successfully authenticated!");
    } catch (AuthenticationException e) {
      printer.printError("Authentication failed: " + e.getMessage());
    }
  }

  public void logout() {
    SecurityContextHolder.clearContext();
    printer.printInfo("You have been logged out.");
  }

  public boolean isLoggedIn() {
    return (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())
        && SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
  }

  public String getLoggedInUsername() {
    if (isLoggedIn()) {
      return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    return null;
  }
}
