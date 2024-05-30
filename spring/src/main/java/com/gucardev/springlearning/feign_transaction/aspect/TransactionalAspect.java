package com.gucardev.springlearning.feign_transaction.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Aspect
@Component
@Slf4j
public class TransactionalAspect {


    @Autowired
    private RestTemplate restTemplate;

    @AfterThrowing(pointcut = "execution(* *(..)) && @annotation(org.springframework.transaction.annotation.Transactional)", throwing = "ex")
    public void rollback(Exception ex) {
        List<RequestDetails> detailsList = RequestDetailsHolder.getRequestDetails();
        for (RequestDetails detail : detailsList) {
            String rollbackUrl = detail.getBaseUrl() + detail.getUrl() + "?rollback=true";
            HttpHeaders headers = new HttpHeaders();
            detail.getHeaders().forEach(headers::set);
            HttpEntity<String> entity = new HttpEntity<>(detail.getBody(), headers);
            ResponseEntity<String> response = null;
            if ("GET".equalsIgnoreCase(detail.getMethod())) {
                response = restTemplate.exchange(rollbackUrl, HttpMethod.GET, entity, String.class);
            } else if ("POST".equalsIgnoreCase(detail.getMethod())) {
                response = restTemplate.exchange(rollbackUrl, HttpMethod.POST, entity, String.class);
            }
            // Handle other HTTP methods as needed
            log.info("Name: {} | Rollback response: {}", detail.getUrl(), response.getStatusCode());
        }
        RequestDetailsHolder.clear();
    }

}
