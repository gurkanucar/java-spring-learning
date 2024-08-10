package org.gucardev.springshell.util;

import org.gucardev.springshell.model.Pharmacy;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class PharmacyFormatter {
  private static String[] toRow(Pharmacy c) {
    return new String[] {c.name(), c.address(), c.phone()};
  }

  public String coverToTable(List<Pharmacy> items) {
    var data = items.stream().map(PharmacyFormatter::toRow).collect(Collectors.toList());
    data.add(0, new String[] {"name", "address", "phone"});

    ArrayTableModel model = new ArrayTableModel(data.toArray(Object[][]::new));
    TableBuilder table = new TableBuilder(model);
    table.addHeaderAndVerticalsBorders(BorderStyle.fancy_light);
    return table.build().render(100);
  }
}
