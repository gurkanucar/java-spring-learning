package org.gucardev.actuator;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ExceptionAspect {

    @AfterThrowing(pointcut = "execution(* org.gucardev.actuator..*(..))" +
            " && !within(org.gucardev.actuator.LogOncePerReqFilter)", throwing = "ex")
    public void handleExceptions(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().toShortString();
        String arguments = Arrays.toString(joinPoint.getArgs());
        log.error("Method: {}, Arguments: {}, Exception: {}", methodName, arguments, ex.getMessage(), ex);
    }

}
