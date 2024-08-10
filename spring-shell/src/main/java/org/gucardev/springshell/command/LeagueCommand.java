package org.gucardev.springshell.command;

import org.gucardev.springshell.model.League;
import org.gucardev.springshell.remote.LeagueApiClient;
import org.gucardev.springshell.util.ShellPrinter;
import org.gucardev.springshell.util.TeamResponseFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Command(group = "League Commands")
public class LeagueCommand {

  private final LeagueApiClient leagueApiClient;
  private final TeamResponseFormatter teamResponseFormatter;
  private final ShellPrinter printer;

  @Command(command = "league -l", description = "get list")
  public void leagueList() {
    List<League> scores = Objects.requireNonNull(leagueApiClient.getAllScores().getBody()).result();
    printer.print(teamResponseFormatter.coverToTable(scores));
  }
}
