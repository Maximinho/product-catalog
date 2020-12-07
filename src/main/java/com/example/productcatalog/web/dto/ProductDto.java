package com.example.productcatalog.web.dto;

public class ProductDto {
    private final long id;
    private final String name;
    private final long categoryId;

    public ProductDto(long id, String name, long categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getCategoryId() {
        return categoryId;
    }
}
