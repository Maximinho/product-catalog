package com.example.productcatalog.exception;

public class IllegalParentCategoryException extends RuntimeException {
    public IllegalParentCategoryException(String message) {
        super(message);
    }
}
