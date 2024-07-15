package org.gucardev.utilities.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "error",
        "code",
        "message",
        "time",
})
public class ExceptionResponse {

    private HttpStatus status;
    private int code;
    private Object message;
    private LocalDateTime time;

    private ExceptionResponse(Builder builder) {
        this.status = builder.status;
        this.code = builder.code;
        this.message = builder.message;
        this.time = LocalDateTime.now();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private HttpStatus status;
        private int code;
        private Object message;

        private Builder() {
            this.status = HttpStatus.OK;
            this.code = ResponseConstants.FAILURE.getCode();
            this.message = ResponseConstants.FAILURE.getMessage();
        }

        public Builder status(HttpStatus status) {
            this.status = status;
            this.code = status.value();
            return this;
        }

        public Builder status(int statusCode) {
            this.status = HttpStatus.resolve(statusCode);
            if (this.status == null) {
                throw new IllegalArgumentException("Invalid HTTP status code: " + statusCode);
            }
            this.code = statusCode;
            return this;
        }

        public Builder error(Object obj) {
            this.code = ResponseConstants.FAILURE.getCode();
            this.message = obj;
            return this;
        }

        public ResponseEntity<ExceptionResponse> build() {
            return ResponseEntity.status(status).body(new ExceptionResponse(this));
        }
    }
}
