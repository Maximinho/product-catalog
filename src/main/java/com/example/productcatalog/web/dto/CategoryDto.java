package com.example.productcatalog.web.dto;

public class CategoryDto {
    private final long id;
    private final String name;
    private final long parentCategoryId;

    public CategoryDto(long id, String name, Long parentCategoryId) {
        this.id = id;
        this.name = name;
        this.parentCategoryId = parentCategoryId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getParentCategoryId() {
        return parentCategoryId;
    }
}
