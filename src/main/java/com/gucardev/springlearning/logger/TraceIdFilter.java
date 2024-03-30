package com.gucardev.springlearning.logger;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Order(1)
@Component
public class TraceIdFilter extends OncePerRequestFilter {
    private static final String TRACE_ID_HEADER = "X-Trace-Id";
    private static final String TRACE_ID_LOG_VAR_NAME = "traceId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String traceId = request.getHeader(TRACE_ID_HEADER);
            if (traceId == null) {
                traceId = UUID.randomUUID().toString();
            }
            MDC.put(TRACE_ID_LOG_VAR_NAME, traceId);

            // Add the traceId to the response header
            response.addHeader(TRACE_ID_HEADER, traceId);

            // Wrap the request to add the traceId to it
            String finalTraceId = traceId;
            HttpServletRequest wrappedRequest = new HttpServletRequestWrapper(request) {
                @Override
                public String getHeader(String name) {
                    if (TRACE_ID_HEADER.equals(name)) {
                        return finalTraceId;
                    }
                    return super.getHeader(name);
                }
            };

            filterChain.doFilter(wrappedRequest, response);
        } finally {
            MDC.remove(TRACE_ID_LOG_VAR_NAME);
        }
    }


}
