package org.gucardev.springshell.util;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ShellPrinter {

  public void print(String message) {
    System.out.println(message);
  }

  public void print(String message, Ansi.Color color) {
    AnsiConsole.systemInstall();
    System.out.println(Ansi.ansi().fg(color).a(message).reset());
    AnsiConsole.systemUninstall();
  }

  public void printSuccess(String message) {
    print(message, Ansi.Color.GREEN);
  }

  public void printWarning(String message) {
    print(message, Ansi.Color.YELLOW);
  }

  public void printError(String message) {
    print(message, Ansi.Color.RED);
  }

  public void printInfo(String message) {
    print(message, Ansi.Color.CYAN);
  }

  public void printTable(String... columns) {
    printTable(Ansi.Color.DEFAULT, columns);
  }

  public void printTable(Ansi.Color color, String... columns) {
    int maxLength = Arrays.stream(columns).map(String::length).max(Integer::compareTo).orElse(0);

    // Print top border
    var topBorder = "┌" + "─".repeat(maxLength + 2) + "┐";
    print(topBorder, color);

    // Print column headers
    for (String column : columns) {
      column = ("│ %-" + maxLength + "s │").formatted(column);
      print(column, color);
    }

    // Print bottom border
    var bottomBorder = "└" + "─".repeat(maxLength + 2) + "┘";
    print(bottomBorder, color);
  }
}
