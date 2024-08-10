package org.gucardev.springshell.util;

import org.gucardev.springshell.model.League;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class TeamResponseFormatter {
  private static String[] toRow(League c) {
    return new String[] {
      "%s - %s".formatted(c.rank(), c.team()), c.lose(), c.win(), c.play(), c.point()
    };
  }

  public String coverToTable(List<League> items) {
    var data = items.stream().map(TeamResponseFormatter::toRow).collect(Collectors.toList());
    data.add(0, new String[] {"team", "lose", "win", "play", "point"});

    ArrayTableModel model = new ArrayTableModel(data.toArray(Object[][]::new));
    TableBuilder table = new TableBuilder(model);
    table.addHeaderAndVerticalsBorders(BorderStyle.fancy_light);
    return table.build().render(100);
  }
}
