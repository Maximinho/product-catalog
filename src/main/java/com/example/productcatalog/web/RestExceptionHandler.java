package com.example.productcatalog.web;

import com.example.productcatalog.exception.IllegalParentCategoryException;
import com.example.productcatalog.exception.ResourceNotFoundException;
import com.example.productcatalog.web.dto.error.ApiError;
import com.example.productcatalog.web.dto.error.ApiValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<ApiValidationError> validationErrors = ex.getFieldErrors()
                .stream()
                .map(fieldError -> new ApiValidationError(fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation error", validationErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(IllegalParentCategoryException.class)
    ResponseEntity<ApiError> handleIllegalParentCategory(IllegalParentCategoryException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }
}
