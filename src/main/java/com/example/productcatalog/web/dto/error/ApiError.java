package com.example.productcatalog.web.dto.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiError {

    private final HttpStatus status;
    private final int statusCode;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<? extends ApiSubError> subErrors;

    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
    }

    public ApiError(HttpStatus status, String message, List<? extends ApiSubError> subErrors) {
        this(status, message);
        this.subErrors = subErrors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public List<? extends ApiSubError> getSubErrors() {
        return subErrors;
    }
}
