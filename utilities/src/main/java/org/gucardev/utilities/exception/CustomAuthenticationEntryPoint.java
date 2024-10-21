package org.gucardev.utilities.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final String TRACE_ID_LOG_VAR_NAME = "traceId";
    private final MessageSource messageSource;

    public CustomAuthenticationEntryPoint(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String path = request.getServletPath();
        String traceId = MDC.get(TRACE_ID_LOG_VAR_NAME);
        String errorMessage = messageSource.getMessage(ExceptionMessage.AUTHENTICATION_FAILED.getKey(), null, LocaleContextHolder.getLocale());
        ExceptionResponse errorResponse = ExceptionResponse.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .code(HttpStatus.UNAUTHORIZED.value())
                .message(errorMessage)
                .path(path)
                .traceId(traceId)
                .hasValidationErrors(false)
                .build();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    }
}
