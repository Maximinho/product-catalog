package com.example.productcatalog.mapper;

import com.example.productcatalog.model.Category;
import com.example.productcatalog.web.dto.CategoryDto;
import com.example.productcatalog.web.request.CategoryRequest;

public interface CategoryMapper {
    Category toEntity(CategoryRequest categoryRequest);

    CategoryDto toDto(Category category);
}
