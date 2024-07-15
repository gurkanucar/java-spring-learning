package org.gucardev.strategypatternwithenums.simple.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandDto {
    private int commandType;
    private String value;
}
