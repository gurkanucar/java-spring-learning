package org.gucardev.actuator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.gucardev.actuator.exception.CustomException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ExceptionAspect {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @AfterThrowing(pointcut = "execution(* org.gucardev.actuator..*(..))" +
            " && !within(org.gucardev.actuator.LogOncePerReqFilter)", throwing = "ex")
    public void handleExceptions(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().toShortString();
        String arguments = formatArguments(joinPoint.getArgs());

        if (ex instanceof CustomException) {
            // Log as a warning for CustomException
            log.warn("exceptionAspect | CustomException in Method: {}, Arguments: {}, Message: {}",
                    methodName, arguments.replaceAll("\"", "'"), ex.getMessage());
        } else {
            // Log as an error for other exceptions
            log.error("exceptionAspect | Method: {}, Arguments: {}, Exception: {}",
                    methodName, arguments.replaceAll("\"", "'"), ex.getMessage(), ex);
        }
    }

    private String formatArguments(Object[] args) {
        try {
            return objectMapper.writeValueAsString(args);
        } catch (JsonProcessingException e) {
            return Arrays.toString(args);
        }
    }
}
