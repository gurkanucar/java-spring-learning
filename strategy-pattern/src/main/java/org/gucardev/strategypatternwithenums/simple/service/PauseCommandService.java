package org.gucardev.strategypatternwithenums.simple.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gucardev.strategypatternwithenums.simple.enumeration.CommandType;
import org.gucardev.strategypatternwithenums.simple.dto.CommandDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service(CommandType.PAUSE_BEAN_NAME)
@Slf4j
public class PauseCommandService implements Command {
    @Override
    public void execute(CommandDto commandDto) {
        log.info("PAUSE | {}", commandDto.getValue());
    }
}
