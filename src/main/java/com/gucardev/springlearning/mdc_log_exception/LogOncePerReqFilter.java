package com.gucardev.springlearning.mdc_log_exception;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.UUID;

@Component
public class LogOncePerReqFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogOncePerReqFilter.class);

    private static final String TRACE_ID_HEADER = "X-Trace-Id";
    private static final String TRACE_ID_LOG_VAR_NAME = "traceId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Generate and set the trace ID
        String traceId = UUID.randomUUID().toString().substring(0, 13);
        MDC.put(TRACE_ID_LOG_VAR_NAME, traceId);
        response.setHeader(TRACE_ID_HEADER, traceId);

        // Wrap the request and response before proceeding with the filter chain
        ContentCachingRequestWrapper cachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper cachingResponseWrapper = new ContentCachingResponseWrapper(response);

        // Proceed with the filter chain
        filterChain.doFilter(cachingRequestWrapper, cachingResponseWrapper);

        // Log the request and response after the filter chain has processed
        String requestBody = getValueAsString(cachingRequestWrapper.getContentAsByteArray(), cachingRequestWrapper.getCharacterEncoding())
                .trim().replaceAll("[\n\r]", "");
        LOGGER.info("Request | {}", requestBody);

        String responseBody = getValueAsString(cachingResponseWrapper.getContentAsByteArray(), cachingResponseWrapper.getCharacterEncoding())
                .trim().replaceAll("[\n\r]", "");
        LOGGER.info("Response | {}", responseBody);

        // Ensure the response is complete
        cachingResponseWrapper.copyBodyToResponse();

        // Clear the MDC
        MDC.remove(TRACE_ID_LOG_VAR_NAME);
    }

    private String getValueAsString(byte[] contentAsByteArray, String characterEncoding) {
        String dataAsString = "";
        try {
            dataAsString = new String(contentAsByteArray, characterEncoding);
        } catch (Exception e) {
            LOGGER.error("Exception occurred while converting byte array to string: {}", e.getMessage());
            e.printStackTrace();
        }
        return dataAsString;
    }
}
