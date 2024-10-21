package org.gucardev.utilities.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "error",
        "code",
        "message",
        "path",
        "time",
        "validationErrors",
        "hasValidationErrors"
})
public class ExceptionResponse {

    private final HttpStatus status;
    private final int code;
    private final String traceId;
    private final Object message;
    private final String path;
    private final Map<String, String> validationErrors;
    private final boolean hasValidationErrors;

    @Builder.Default
    private final String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    public static ResponseEntity<ExceptionResponse> buildResponse(HttpStatus status, Object message, String path, String traceId, Map<String, String> validationErrors) {
        ExceptionResponse response = ExceptionResponse.builder()
                .status(status)
                .code(status.value())
                .message(message)
                .path(path)
                .traceId(traceId)
                .validationErrors(validationErrors)
                .hasValidationErrors(validationErrors != null && !validationErrors.isEmpty())
                .build();
        return ResponseEntity.status(status).body(response);
    }
}

