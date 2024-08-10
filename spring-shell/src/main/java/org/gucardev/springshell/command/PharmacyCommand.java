package org.gucardev.springshell.command;

import org.gucardev.springshell.remote.PharmacyApiClient;
import org.gucardev.springshell.util.PharmacyFormatter;
import org.gucardev.springshell.util.ShellPrinter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import java.util.Objects;

@RequiredArgsConstructor
@Command(group = "Pharmacy Commands")
public class PharmacyCommand {

  private final PharmacyApiClient pharmacyApiClient;
  private final PharmacyFormatter pharmacyFormatter;
  private final ShellPrinter printer;

  @Command(command = "pharmacy", description = "Get duty pharmacy")
  public void pharmacy(
      @NotBlank
          @Size(min = 2)
          @Option(shortNames = 'c', longNames = "city", description = "city name..")
          String city,
      @Size(min = 2)
          @Option(shortNames = 'd', longNames = "district", description = "district name..")
          String district) {

    printer.print(
        pharmacyFormatter.coverToTable(
            Objects.requireNonNull(pharmacyApiClient.getPharmacies(city, district).getBody())
                .result()));
  }
}
