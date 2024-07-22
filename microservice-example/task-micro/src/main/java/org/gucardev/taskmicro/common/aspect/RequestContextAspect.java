package org.gucardev.taskmicro.common.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.gucardev.taskmicro.common.context.RequestInteractionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestContextAspect {

    @Autowired
    private RequestInteractionContext requestInteractionContext;

    @Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public void scheduledMethod() {
    }

    @Before("scheduledMethod()")
    public void beforeScheduledMethod() {
        requestInteractionContext.setRequestWithoutInteractionTrue();
    }

    @After("scheduledMethod()")
    public void afterScheduledMethod() {
        requestInteractionContext.clearContext();

    }
}
