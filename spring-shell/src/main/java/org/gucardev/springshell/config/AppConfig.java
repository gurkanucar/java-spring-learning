package org.gucardev.springshell.config;

import org.gucardev.springshell.exception.CLIExceptionResolver;
import org.gucardev.springshell.util.ShellReader;
import org.jline.reader.LineReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {

  @Bean
  CLIExceptionResolver customExceptionResolver() {
    return new CLIExceptionResolver();
  }

  @Bean
  public ShellReader shellReader(@Lazy LineReader lineReader) {
    return new ShellReader(lineReader);
  }
}
