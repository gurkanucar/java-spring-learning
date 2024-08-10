package org.gucardev.springshell.command;

import org.gucardev.springshell.service.AuthService;
import org.gucardev.springshell.util.ShellReader;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;

import java.io.IOException;

@Command(group = "Login Commands")
@RequiredArgsConstructor
public class AuthCommand {

  private final ShellReader shellReader;
  private final AuthService authService;

  @Command(command = "login", description = "login method")
  public void login(
      //  @Option(shortNames = 'u') String username, @Option(shortNames = 'p') String password
      ) throws IOException {
    String username = shellReader.readLine("Please enter your username");
    String password = shellReader.readLinePassword("Please enter your password");
    authService.login(username, password);
  }

  @Command(command = "logout", description = "Logout method")
  public void logout() {
    authService.logout();
  }
}
