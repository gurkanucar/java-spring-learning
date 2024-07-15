package org.gucardev.mixed.relationship_cases.feign_transaction.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.Map;


@Aspect
@Component
@Slf4j
public class RollbackAspect {

    @Autowired
    private ApplicationContext context;

    @AfterThrowing(pointcut = "execution(* *(..)) && @annotation(org.springframework.transaction.annotation.Transactional)", throwing = "ex")
    public void rollback(Exception ex) {
        // Iterate over all beans and find those annotated with @CustomFeignAnnotation
        // MAKE IT METHOD -> TYPE in annotation
        Map<String, Object> beans = context.getBeansWithAnnotation(RollbackApi.class);
        for (Object bean : beans.values()) {
            RollbackApi rollbackAnnotation = AnnotationUtils.findAnnotation(bean.getClass(), RollbackApi.class);
            if (rollbackAnnotation != null) {
                String rollbackUrl = rollbackAnnotation.rollbackUrl();
                // Call the rollback endpoint
                log.info(rollbackUrl);
            }
        }

    }
}
