package org.gucardev.strategypatternwithenums.simple.service;

import org.gucardev.strategypatternwithenums.simple.dto.CommandDto;

public interface Command {
    void execute(CommandDto commandDto);
}
