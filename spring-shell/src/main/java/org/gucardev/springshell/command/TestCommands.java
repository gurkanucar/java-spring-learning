package org.gucardev.springshell.command;
import org.fusesource.jansi.Ansi;
import org.gucardev.springshell.util.ShellPrinter;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.shell.command.annotation.Option;

@Command(group = "Test Commands")
@RequiredArgsConstructor
public class TestCommands {

  private final ShellPrinter printer;

  @Command(command = "print", description = "printTest command")
  public void printTest() {
    printer.print("This is a regular message.");
    printer.printSuccess("This is a success message.");
    printer.printWarning("This is a warning message.");
    printer.printError("This is an error message.");
    printer.printInfo("This is an info message.");
  }

  @Command(command = "public", description = "public command")
  public void publicCommand(
      @Option(required = false, defaultValue = "", shortNames = 'n') String name) {
    printer.print("Hello %s from public command!".formatted(name), Ansi.Color.CYAN);
  }

  @CommandAvailability(provider = "userLoggedInProvider")
  @Command(command = "private", description = "private command")
  public void privateCommand(
      @Option(required = false, defaultValue = "", shortNames = 'n') String name) {
    printer.print("Hello %s from private command!".formatted(name), Ansi.Color.YELLOW);
  }
}
