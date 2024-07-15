package org.gucardev.strategypatternwithenums.simple.factory;

import lombok.extern.slf4j.Slf4j;
import org.gucardev.strategypatternwithenums.simple.dto.CommandDto;
import org.gucardev.strategypatternwithenums.simple.enumeration.CommandType;
import org.gucardev.strategypatternwithenums.simple.service.Command;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class CommandHandlerFactory {

    private final Map<String, Command> commandHandlersMap;

    public CommandHandlerFactory(Map<String, Command> commandHandlersMap) {
        this.commandHandlersMap = commandHandlersMap;
    }

    public void executeCommand(CommandDto commandDto) {
        var commandType = CommandType.getCommandType(commandDto.getCommandType());
        log.info("Selected command:{}", commandType.beanName());
        commandHandlersMap.get(commandType.beanName()).execute(commandDto);
    }

}
