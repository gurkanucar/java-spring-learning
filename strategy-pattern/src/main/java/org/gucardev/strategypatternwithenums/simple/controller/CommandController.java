package org.gucardev.strategypatternwithenums.simple.controller;


import lombok.RequiredArgsConstructor;
import org.gucardev.strategypatternwithenums.simple.factory.CommandHandlerFactory;
import org.gucardev.strategypatternwithenums.simple.dto.CommandDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/command")
@RequiredArgsConstructor
public class CommandController {

    private final CommandHandlerFactory commandHandlerFactory;

    @PostMapping
    public ResponseEntity<?> sendCommand(@RequestBody CommandDto commandDto) {
        commandHandlerFactory.executeCommand(commandDto);
        return ResponseEntity.ok().build();
    }

}
