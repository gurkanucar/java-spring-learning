package org.gucardev.utilities.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ExceptionAspect {

    private static final String TRACE_ID_LOG_VAR_NAME = "traceId";

    @AfterThrowing(pointcut = "execution(* org.gucardev.utilities..*(..))" +
//            " && !within(org.gucardev.utilities.security.JwtFilter)" +
            " && !within(org.gucardev.utilities.config.LogOncePerReqFilter)", throwing = "ex")
    public void handleExceptions(JoinPoint joinPoint, Exception ex) {
        String traceId = MDC.get(TRACE_ID_LOG_VAR_NAME);
        String methodName = joinPoint.getSignature().toShortString();
        String arguments = Arrays.toString(joinPoint.getArgs());
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        log.error("Trace ID: {}, Timestamp: {}, Method: {}, Arguments: {}, Exception: {}",
                traceId, timestamp, methodName, arguments, ex.getMessage(), ex);
    }

}
