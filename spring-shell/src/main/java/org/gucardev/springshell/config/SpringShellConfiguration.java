package org.gucardev.springshell.config;

import org.gucardev.springshell.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;

@Configuration
@RequiredArgsConstructor
public class SpringShellConfiguration {

  private final AuthService authService;

  @Bean
  public PromptProvider promptProvider() {
    return () -> {
      AttributedStyle style = determinePromptStyle();
      String promptString = buildPromptString();
      return new AttributedString(promptString, style);
    };
  }

  private AttributedStyle determinePromptStyle() {
    if (authService.isLoggedIn()) {
      return AttributedStyle.DEFAULT
          .background(AttributedStyle.GREEN)
          .foreground(AttributedStyle.BLACK);
    } else {
      return AttributedStyle.DEFAULT
          .background(AttributedStyle.RED)
          .foreground(AttributedStyle.WHITE);
    }
  }

  private String buildPromptString() {
    String username = authService.getLoggedInUsername();
    return "myapp" + (username != null ? ":" + username : "") + " > ";
  }
}
