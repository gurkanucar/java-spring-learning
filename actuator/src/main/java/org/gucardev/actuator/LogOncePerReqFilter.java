package org.gucardev.actuator;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
@Slf4j
public class LogOncePerReqFilter extends OncePerRequestFilter {

    private static final int MAX_LOG_CONTENT_LENGTH = 2000;
    private static final String TRACE_ID_HEADER = "X-Trace-Id";
    private static final String MDC_TRACE_ID_KEY = "traceId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        long startTime = System.currentTimeMillis();

        ContentCachingRequestWrapper cachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper cachingResponseWrapper = new ContentCachingResponseWrapper(response);

        try {
            response.setHeader(TRACE_ID_HEADER, MDC.get(MDC_TRACE_ID_KEY));
            filterChain.doFilter(cachingRequestWrapper, cachingResponseWrapper);
        } finally {
            long duration = System.currentTimeMillis() - startTime;

            logContent("Request", cachingRequestWrapper.getContentAsByteArray(), cachingRequestWrapper.getCharacterEncoding(), duration, cachingRequestWrapper);
            logContent("Response", cachingResponseWrapper.getContentAsByteArray(), cachingResponseWrapper.getCharacterEncoding(), duration, null);

            cachingResponseWrapper.copyBodyToResponse();
        }
    }

    private void logContent(String type, byte[] content, String characterEncoding, long duration, HttpServletRequest request) {
        String logData = getValueAsString(content, characterEncoding)
                .trim().replaceAll("[\n\r]", "");
        logData = logData.substring(0, Math.min(logData.length(), MAX_LOG_CONTENT_LENGTH));

        if (request != null) {
            StringBuilder params = new StringBuilder();
            request.getParameterMap().forEach((key, values) -> {
                for (String value : values) {
                    params.append(key).append("=").append(value).append("&");
                }
            });

            if (!params.isEmpty()) {
                params.setLength(params.length() - 1);
            }

            String path = request.getRequestURI();

            log.info("{} | Path: {}?{} | Body: {}", type, path, params, logData);
        } else {
            log.info("{} | duration: {} ms | {}", type, duration, logData);
        }
    }

    private String getValueAsString(byte[] contentAsByteArray, String characterEncoding) {
        String dataAsString = "";
        try {
            dataAsString = new String(contentAsByteArray, characterEncoding);
        } catch (Exception e) {
            log.error("Exception occurred while converting byte array to string: {}", e.getMessage());
        }
        return dataAsString;
    }
}
