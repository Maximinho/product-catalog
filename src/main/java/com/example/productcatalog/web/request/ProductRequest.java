package com.example.productcatalog.web.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductRequest {

    @NotBlank
    private final String name;

    @NotNull
    private final Long categoryId;

    public ProductRequest(String name, Long categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
