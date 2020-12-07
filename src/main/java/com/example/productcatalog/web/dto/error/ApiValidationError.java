package com.example.productcatalog.web.dto.error;

public class ApiValidationError extends ApiSubError {
    private final String field;
    private final Object rejectedValue;
    private final String message;

    public ApiValidationError(String field, Object rejectedValue, String message) {
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public String getMessage() {
        return message;
    }
}
