package org.gucardev.mixed.relationship_cases.feign_transaction.aspect;

import lombok.Data;

import java.util.Collection;
import java.util.Map;

@Data
public class RequestDetails {
    private String baseUrl;
    private String url;
    private String method;
    private Map<String, String> headers;
    private String body;
    private Map<String, Collection<String>> queryParams;
}
