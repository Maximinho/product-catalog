package com.example.productcatalog.web.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CategoryRequest {

    @NotBlank
    private final String name;

    @NotNull
    private final Long parentCategoryId;

    public CategoryRequest(String name, Long parentCategoryId) {
        this.name = name;
        this.parentCategoryId = parentCategoryId;
    }

    public String getName() {
        return name;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }
}
