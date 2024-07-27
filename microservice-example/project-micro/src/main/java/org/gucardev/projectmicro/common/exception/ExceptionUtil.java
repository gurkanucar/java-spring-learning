package org.gucardev.projectmicro.common.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ExceptionUtil {
  private static MessageSource messageSource;

  public ExceptionUtil() {}

  public static CustomException buildException() {
    return buildException(ExceptionMessage.DEFAULT_EXCEPTION);
  }

  public static CustomException buildException(String ex) {
    return new CustomException(ex, ExceptionMessage.DEFAULT_EXCEPTION.getStatus());
  }

  public static CustomException buildException(ExceptionMessage ex, Object... args) {
    String errorMessage =
        messageSource.getMessage(ex.getKey(), args, LocaleContextHolder.getLocale());
    return new CustomException(errorMessage, ex.getStatus());
  }

  public static CustomException buildException(ExceptionMessage ex) {
    String errorMessage =
        messageSource.getMessage(ex.getKey(), null, LocaleContextHolder.getLocale());
    return new CustomException(errorMessage, ex.getStatus());
  }

  @Autowired
  public void setMessageSource(MessageSource messageSource) {
    ExceptionUtil.messageSource = messageSource;
  }
}
