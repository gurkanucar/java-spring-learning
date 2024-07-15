package org.gucardev.strategypatternwithenums.simple.enumeration;

import lombok.Getter;

@Getter
public enum CommandType {
    PLAY(1, CommandType.PLAY_BEAN_NAME),
    PAUSE(2, CommandType.PAUSE_BEAN_NAME);

    public static final String PLAY_BEAN_NAME = "playHandler";
    public static final String PAUSE_BEAN_NAME = "pauseHandler";

    private final int commandType;
    private final String beanName;

    CommandType(int commandType, String beanName) {
        this.commandType = commandType;
        this.beanName = beanName;
    }

    public String beanName() {
        return beanName;
    }

    public static CommandType getCommandType(int commandType) {
        for (CommandType type : CommandType.values()) {
            if (type.commandType == commandType) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown command type: " + commandType);
    }
}
