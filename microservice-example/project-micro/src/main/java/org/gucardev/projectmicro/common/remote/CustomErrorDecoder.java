package org.gucardev.projectmicro.common.remote;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.gucardev.projectmicro.common.exception.CustomException;
import org.gucardev.projectmicro.common.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;


@Component
public class CustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.body() != null) {
            try (InputStream bodyIs = response.body().asInputStream()) {
                ExceptionResponse exceptionResponse = objectMapper.readValue(bodyIs, ExceptionResponse.class);
                return mapException(exceptionResponse, response.status());
            } catch (IOException e) {
                return new CustomException("Error parsing error response: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }

    private Exception mapException(ExceptionResponse exceptionResponse, int statusCode) {
        HttpStatus status = HttpStatus.resolve(statusCode);
        if (status != null) {
            return switch (status) {
                case BAD_REQUEST -> new CustomException(exceptionResponse.getMessage().toString(), status);
                case NOT_FOUND -> new CustomException("Not found", status);
                case UNAUTHORIZED, FORBIDDEN -> new CustomException("Access denied", status);
                default -> new CustomException("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
            };
        }
        return new CustomException("Unknown error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
