package org.gucardev.actuator.exception;

import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class ExceptionUtil {

    public ExceptionUtil() {
    }

    public static CustomException buildException() {
        return buildException(ExceptionMessage.DEFAULT_EXCEPTION);
    }

    public static CustomException buildException(String ex) {
        return new CustomException(ex, ExceptionMessage.DEFAULT_EXCEPTION.getStatus());
    }

    public static CustomException buildException(ExceptionMessage ex, Object... args) {
        String errorMessage;
        if (args == null || args.length == 0) {
            errorMessage = getErrorMessage(ex);
        } else {
            errorMessage = MessageFormat.format(ex.getMessage(), args);
        }
        return new CustomException(errorMessage, ex.getStatus());
    }

    public static CustomException buildException(ExceptionMessage ex) {
        return new CustomException(getErrorMessage(ex), ex.getStatus());
    }

    private static String getErrorMessage(ExceptionMessage ex) {
        return ex.getMessage().replaceAll("\\{\\d+}", "");
    }

}